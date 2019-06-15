package com.bank.controller;

import java.math.BigDecimal;
import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.ResponseEntity.BodyBuilder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.web.util.UriComponentsBuilder;

import com.bank.entites.Account;
import com.bank.entites.Transaction;
import com.bank.requestbody.TransferRequest;
import com.bank.service.BankService;
import com.bank.service.BankServiceImpl;
import com.bank.service.ExceptionServiceImpl;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
public class BankController {

	private Logger logger = LoggerFactory.getLogger(BankController.class);

	@Autowired
	BankService bankService;

	@GetMapping("/account/{accountId}/allTransactions")
	public List<Transaction> findByIdAccountStatement(@PathVariable("accountId") long accountId) {
		try {
			logger.info("findByIdAccountStatement: account {}", accountId);
			return (bankService.findAllTransactions(accountId));
		} catch (Exception e) {
			logger.error("findByIdAccountStatement: error: {}", e.getMessage());
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage(), e);
		}
	}
	
	@GetMapping("/account/{accountId}/balance")
	public BigDecimal customerBalance(@PathVariable("accountId") long accountId) {
		try {
			logger.info("customerBalance: account {}", accountId);
			return (bankService.AccountBalance(accountId));
		} catch (Exception e) {
			logger.error("findByIdAccountStatement: error: {}", e.getMessage());
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage(), e);
		}
	}

	@SuppressWarnings("unchecked")
	@PostMapping(path = "/account/transfer", consumes = "application/json", produces = "application/json")
	public ResponseEntity<Object> transfer(@Valid @RequestBody TransferRequest request) {
		try {
			Account account = bankService.transfer(request.getAccountFrom(), request.getAccountTo(),request.getAmount());
			HttpHeaders headers = new HttpHeaders();
			logger.info("Request: {", request , "}");
			headers.add("Access-Control-Expose-Headers", "Location");
			
			 URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                     .path("/{id}")
                     .buildAndExpand(account.getId())
                     .toUri();
			 //Send location in response
			 return (ResponseEntity<Object>) ((BodyBuilder) ResponseEntity
					 .created(location).build());
					
			// return new ResponseEntity<Object>((headers),HttpStatus.CREATED);
		} catch (ExceptionServiceImpl e) {
			logger.error("transfer: error: {}", e.getMessage());
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage(), e);
		}

	}

}
