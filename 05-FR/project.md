# Auction system

## Introduction

Specification of functional requirements as part of computerisation of the product sale process based on the auction mechanism.

## Business processes

---
<a id="bc1"></a>
### BC1: Auction sale

**Actors:** [Seller](#ac1), [Buyer](#ac2)

**Description:** Business process describing a sale by the auction mechanism.

**Main scenario:**
1. [Seller](#ac1) offers the product at an auction. ([UC1](#uc1))
2. [Buyer](#ac2) offers a bid for the product that is higher than the currently highest bid. ([BR1](#br1))
3. [Buyer](#ac2) wins the auction ([BR2](#br2))
4. [Buyer](#ac2) transfers the amount due to the Seller. ([BR3](#br3))
5. [Seller](#ac1) transfers the product to the Buyer. ([UC2](#uc2))
6. [Sellet](#ac1) gets the summary of the whole transaction ([UC3](#uc3))

**Alternative scenarios:** 

2.A. Buyer's bid has been outbid and [Buyer](#ac2) wants to outbid the current highest bid.
* 2.A.1. Continue at step 2.

3.A. Auction time has elapsed and [Buyer](#ac2) has lost the auction. ([BR2](#br2))
* 3.A.1. End of use case.

4.A. Buyer [Buyer](#ac2) doesn't have enough money on his bank account.
* 4.A.1 Inform the buyer that he have to refill his account.
* 4.A.2 Continue at step 4.


## Actors

<a id="ac1"></a>
### AC1: Seller

A person offering goods at an auction.

<a id="ac2"></a>
### AC2: Buyer

A person intending to purchase a product at an auction..


## User level use cases

### Actors and their goals 

[Seller](#ac1):
* [UC1](#uc1): Offering a product at an auction
* [UC2](#uc2): Transfering the product to the Buyer.
* [UC3] (#uc3): Getting summary of the transaction.

[Buyer](#ac2):
* [BR1](#br1): Offering a bid for the product that is higher than the currently highest bid.
* [BR2](#br2): Winning the auction.
* [BR3](#br3): Transfering the amount due to the Seller.

<a id="uc1"></a>
### UC1: Offering a product at an auction

**Actors:** [Seller](#ac1)

**Main scenario:**
1. [Seller](#ac1) reports to the system the willingness to offer the product up at an auction.
2. System asks for the product data and initial price.
3. [Seller](#ac1) provides product data and the initial price.
4. System verifies data correctness.
5. System informs that the product has been successfully put up for auction.

**Alternative scenarios:** 

4.A. Incorrect or incomplete product data has been entered.
* 4.A.1. informs about incorrectly entered data.
* 4.A.2. Continue at step 2.
4.B. The product brakes the rules of the services statute.
* 4.B.1. shows the adequate point from the statute.
* 4.B.2. End of the use case.

<a id="uc2"></a>
### UC2: Transfering the product to the buyer

**Actors:** [Seller](#ac1), [Buyer](#ac2)

**Main scenario:**
1. [Seller](#ac1) logs to the system.
2. System veryfies the identity of the Seller.
3. [Seller](#ac1) reports to the system the willigness of checking the delivery address.
4. System shows the delivery address.
5. [Seller](#ac1) marks in the system, that the product was send to the buyer.
6. System informs Buyer, that the product was send.
7. [Buyer](#ac2) send to system information about the quality of the delivery.
8. System verifies the comment from the buyer.
9. System mark the Seller as trustworthy.

**Alternative scenarios:** 

2.A. Incorrect password has been entered.
* 2.A.1. informs about wrong password.
* 2.A.2. continue at step 1.
2.B. Non-existing user e-mail has been entered.
* 2.B.1 informs about inccorect e-mail address
* 2.B.2 continue at step 1.
8.A. Bad opinion from the buyer.
* 8.A.1. End of the user case.

<a id="uc3"></a>
### UC3: Getting summary of the transaction.

**Actors:** [Seller](#ac1)

**Main scenario:**
1. System sends to the Seller the notification about the end of the transaction.
2. System asks Seller if he want to get the Summary of the transaction.
3. [Seller] gives the response.
3. System sends on the Sellers mail the Summary.
4. [Seller] confirm reciving the e-mail.

3.A. Seller doesn't want to get the Summary.
* 3.A.1 End of the user case.
5.A. Seller dosen't confirm reciving the e-mail.
* 5.A.1 continue at step 2.

## Business objects (also known as domain or IT objects)

### BO1: Auction

The auction is a form of concluding a sale and purchase transaction in which the Seller specifies the starting bid of the product, while the Buyers may offer their own purchase offer, each time proposing a bid higher than the currently offered bid. The auction ends after a specified period of time. If at least one purchase offer has been submitted, the product is purchased by the Buyer who offered the highest bid. 

### BO2: Product

A physical or digital item to be auctioned.

### B03: Bid

Declared amount of money, which the Buyer is going to pay for the product.

### B04: Summary

Document which includes information about the time in which auction took place, the history of bids and date of the final transaction.

## Business rules

<a id="br1"></a>
### BR1: Bidding at auction

Bidding at auction requires submitting an amount higher than current by a minimum of EUR 1.00

<a id="br2"></a>
### BR2: Winning an auction

Auction is won by [Buyer](#ac2) who submitted the highest bid before the end of the auction (time expires).

<a id="br3"></a>
### BR3: Transfering money

Transfering money requires from the [Buyer](#ac2) to connect with his bank service.

## CRUDL Matrix


| Use case                                  | Auction | Product | Bid | Summary |
| ----------------------------------------- | ------- | ------- | --- | ------- |
| UC1: Offering a product at an auction     |    C    |    C    |  C  |    -    |
| UC2: Transfering the product to the Buyer |    -    |    D    |  -  |    -    |
| UC3: Getting summary of the transaction   |    -    |    -    |  -  |   C R   |
| BR1: Offering a bid for the product that  |         |         |     |         |
| is higher than the currently highest bid  |    -    |    -    |  U  |    -    |
| BR2: Winning the auction                  |    D    |    -    |  -  |    -    |
| BR3: Transfering the amount due to        |         |         |     |         |
| the Seller.                               |    -    |    -    | R D |    -    |

