#Health -- Core Schema v.1

## USER
* id:Int
* name:String
* password-md5:String
* password-salt:String
* email:String
* status:String

## DAY
* id:Int
* userId:Int
* date:Date

## NUTRITION ENTRY
* id:Int
* userId:Int
* dateId:Int
* type:Int
* time:Time
* food:String
* calories:Int

##EXERCISE ENTRY
* id:Int
* userId:Int
* dateId: Int
* duration:Long ##store this is seconds
* description

##WEIGHT ENTRY
* id:Int
* userId:Int
* dateId:Int
* time:Time

##BLOOD SUGAR ENTRY
* id:Int
* userId:Int
* dateId:Int
* time:Time
* reading:Int
