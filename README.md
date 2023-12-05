# InternshipRepo
This repository contains serverless functions and cricket score api written by interns during their internship.

## By - DEVANSH TOMAR
## 1-AWS Lambda API Link for getLicenseByLicenseNumber


Request body :
              {licenseNumber }
              
Output:
       {Return license body}
### Example 1:
   ## Request body:
                  {ABC001}
   ## Output:
     {
    "licenseNumber": "ABC001",
    "softwareName": "XYZ PLATFORM",
    "softwareVersion": "1.0.0.0",
    "softwareDescription": "XYZ plaform maintained by infosys",
    "vendorName": "xyz pvt Ltd",
    "vendorUrl": "https://www.microsoft.com/",
    "grantedTo": "Acme Inc.",
    "licensedTo": "Acme Inc.",
    "purpose": "PRODUCTION",
    "additionalInformation": {
        "use": "training"
    },
    "softwareModules": [
        {
            "moduleName": "sls",
            "moduleVersion": "1.0.0.0",
            "constraints": [
                {
                    "name": "vaildFrom",
                    "value": "2021:01:01T16:08:40",
                    "operator": "GREATER_THAN",
                    "errorMessage": "Product not licensed for usage"
                },
                {
                    "name": "vaildTo",
                    "value": "2031:09:09T16:08:40",
                    "operator": "LESS_THAN",
                    "errorMessage": "License expired"
                }
            ],
            "module": {
                "name": "sls",
                "description": null
            }
        }
    ]
    }
    
## API LINK:
           https://l72iwui06c.execute-api.ap-south-1.amazonaws.com/get/getlicense/ABC001
    
    
 ## 2-EmailSender_Functionality
 
 Request params : 
                 {to,subject,message }
   
   Output:
        {Return http status}
        
## API LINK:
           https://ec20wnzbni.execute-api.us-west-2.amazonaws.com/api/email


         
