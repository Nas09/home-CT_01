# home-CT_01

SpringBoot / SopringBatch + JSR 380 + MockServer with Maven build

**Tech Stack:**
* Maven 3+
* SpringBoot 2+
* MockServer 5+


This POC run a Mock Server on http://localhost:8182, to return expected responses when called by the client (the batch).


This poc is a SpringBatch µService, that read a file from resources (ad.json), and generate a file a new file (results.json)
Run HomeCt01Application to generate results.json file.

The results.jon file contains results ads validation Scam (yes / no).

**Rules to match :**

rule::firstname::length 

rule::lastname::length 

rule:✉:alpha_rate

rule:✉:number_rate

rule::price::quotation_rate

rule::registernumber::blacklist






**Below the ad.json file content :** 

```json
[{
	"contacts": {
		"firstName": "Christophe",
		"lastName": "Dupont",
		"email": "testdepot@yopmail.fr",
		"phone1": {
			"value": "0607080901"
		}
	},
	"creationDate": "2020-01-09T09:02:22.610Z",
	"price": 32000,
	"publicationOptions": ["STRENGTHS", "BOOST_VO"],
	"reference": "B300053623",
	"vehicle": {
		"make": "HONDA",
		"model": "CR-V",
		"version": "IV (2) 1.6 I-DTEC 160 4WD EXCLUSIVE NAVI AT",
		"category": "SUV_4X4_CROSSOVER",
		"registerNumber": "BB123",
		"mileage": 100000
	}
},
{
	"contacts": {
		"firstName": "Christophe",
		"lastName": "Dupont",
		"email": "testdepot@yopmail.fr",
		"phone1": {
			"value": "0607080901"
		}
	},
	"creationDate": "2020-01-09T09:02:22.610Z",
	"price": 19000,
	"publicationOptions": ["STRENGTHS", "BOOST_VO"],
	"reference": "B300053623",
	"vehicle": {
		"make": "HONDA",
		"model": "CR-V",
		"version": "IV (2) 1.6 I-DTEC 160 4WD EXCLUSIVE NAVI AT",
		"category": "SUV_4X4_CROSSOVER",
		"registerNumber": "AA123AA",
		"mileage": 100000
	}
},
{
	"contacts": {
		"firstName": "Christophe",
		"lastName": "Du",
		"email": "testdepot@yopmail.fr",
		"phone1": {
			"value": "0607080901"
		}
	},
	"creationDate": "2020-01-09T09:02:22.610Z",
	"price": 32000,
	"publicationOptions": ["STRENGTHS", "BOOST_VO"],
	"reference": "B300053623",
	"vehicle": {
		"make": "HONDA",
		"model": "CR-V",
		"version": "IV (2) 1.6 I-DTEC 160 4WD EXCLUSIVE NAVI AT",
		"category": "SUV_4X4_CROSSOVER",
		"registerNumber": "BB123",
		"mileage": 100000
	}
},
{
	"contacts": {
		"firstName": "Christophe",
		"lastName": "Dupont",
		"email": "test-_-_-_-_-depot@yopmail.fr",
		"phone1": {
			"value": "0607080901"
		}
	},
	"creationDate": "2020-01-09T09:02:22.610Z",
	"price": 32000,
	"publicationOptions": ["STRENGTHS", "BOOST_VO"],
	"reference": "B300053623",
	"vehicle": {
		"make": "HONDA",
		"model": "CR-V",
		"version": "IV (2) 1.6 I-DTEC 160 4WD EXCLUSIVE NAVI AT",
		"category": "SUV_4X4_CROSSOVER",
		"registerNumber": "BB123",
		"mileage": 100000
	}
},
{
	"contacts": {
		"firstName": "Christophe",
		"lastName": "Dupont",
		"email": "testdepot1234567@yopmail.fr",
		"phone1": {
			"value": "0607080901"
		}
	},
	"creationDate": "2020-01-09T09:02:22.610Z",
	"price": 32000,
	"publicationOptions": ["STRENGTHS", "BOOST_VO"],
	"reference": "B300053623",
	"vehicle": {
		"make": "HONDA",
		"model": "CR-V",
		"version": "IV (2) 1.6 I-DTEC 160 4WD EXCLUSIVE NAVI AT",
		"category": "SUV_4X4_CROSSOVER",
		"registerNumber": "BB123",
		"mileage": 100000
	}
},
{
	"contacts": {
		"firstName": "Christophe",
		"lastName": "Dupont",
		"email": "testdepot@yopmail.fr",
		"phone1": {
			"value": "0607080901"
		}
	},
	"creationDate": "2020-01-09T09:02:22.610Z",
	"price": 1,
	"publicationOptions": ["STRENGTHS", "BOOST_VO"],
	"reference": "B300053623",
	"vehicle": {
		"make": "HONDA",
		"model": "CR-V",
		"version": "IV (2) 1.6 I-DTEC 160 4WD EXCLUSIVE NAVI AT",
		"category": "SUV_4X4_CROSSOVER",
		"registerNumber": "BB123",
		"mileage": 100000
	}
},
{
	"contacts": {
		"firstName": "Christophe",
		"lastName": "Dupont",
		"email": "testdepot@yopmail.fr",
		"phone1": {
			"value": "0607080901"
		}
	},
	"creationDate": "2020-01-09T09:02:22.610Z",
	"price": 32000,
	"publicationOptions": ["STRENGTHS", "BOOST_VO"],
	"reference": "B300053623",
	"vehicle": {
		"make": "HONDA",
		"model": "CR-V",
		"version": "IV (2) 1.6 I-DTEC 160 4WD EXCLUSIVE NAVI AT",
		"category": "SUV_4X4_CROSSOVER",
		"registerNumber": "AA123AA",
		"mileage": 100000
	}
},
{
	"contacts": {
		"firstName": "Ch",
		"lastName": "Du",
		"email": "testdepot12123445465-------___--@yopmail.fr",
		"phone1": {
			"value": "0607080901"
		}
	},
	"creationDate": "2020-01-09T09:02:22.610Z",
	"price": 1,
	"publicationOptions": ["STRENGTHS", "BOOST_VO"],
	"reference": "B300053623",
	"vehicle": {
		"make": "HONDA",
		"model": "CR-V",
		"version": "IV (2) 1.6 I-DTEC 160 4WD EXCLUSIVE NAVI AT",
		"category": "SUV_4X4_CROSSOVER",
		"registerNumber": "AA123AA",
		"mileage": 100000
	}
}
]
```




