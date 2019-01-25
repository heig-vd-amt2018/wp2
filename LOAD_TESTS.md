#  Load tests



Having an API that behaves correctly when tested with Cucumber does not mean that it will do so on heavy loads. Load tests are designed to verify concurrency handling and performance limitations. 

In our project the mechanism that might be the most vulnerable to heavy loads is the Event handling but we will be testing a few more scenarios. Those tests are made with [JMeter](https://jmeter.apache.org/) 5.0 that allows to perform multiple queries at the same time, chain queries, check responses and performances.



### Scenarios

##### Basic concurrent creations (jmeter-scripts/creations.jmx)

*Load : 100 app dev posting the same application, Ramp-Up Period : 0 s*

* Try creating application with same API key on `POST /applications` 
  * There should be only one app created with that API key
    * As GET is not implemented by our API, verification is done manually in the database
    * RESULT : PASS

    ![applications](C:\Users\Joel\Documents\HEIG\AMT\Projet\wp2\img\applications.png.jpg)
* Try creating multiple point scales with the same name for that application on `POST /pointScales`
  * There should be only one pointScale created
    * Assertion on response JSON array size == 1 from  `GET /pointscales`
    * RESULT : FAILED, found 6
      * This shows that there is a concurrency problem for Point Scales creation

* Try creating multiple badges with the same attributes for that application on `POST /badges`
  * There should be only one badge created
    * Assertion on response JSON array size == 1 from `GET /badges` 
    * RESULT : FAILED, found 6
      * This shows that there is a concurrency problem for Badges creation
* Try creating multiple rules at the same time for that application on `POST /rules`
  - There should be only one pointScale created
    - RESULT : FAILED, found
      - This shows that there is a concurrency problem for Rules creation



Although this may cause errors (trying to affect an object with several), this is very unlikely to happen, since those content creations are done by the app developper. This means there is very little chance that the app developer sends 2 or more duplicated requests at the same time.



##### Event handling scenario

* Application :
  * An app is created
* PointScale : 
  * a point scale *Posts* is created
* Badges creation:
  * two badges are created *Rookie* and *Expert* 
* Rules creation:
  * a rule *PostingRookie* is created. It delivers the badge *Rookie* to a user on Event type *posting*
  * a rule *PostingPoints* is created. It increases the point scale *Posts* by 1 point on Event type *posting* 
  * a rule *PostingExpert* is created. It delivers the badge *Expert* to a user when the point scale *Posts* reaches 1'000.
* Send 1000 events of type *posting* for the user at the same time



Assertions :

* the user should have exactly 2 badges : *PostingRookie* and *PostingExpert* 

* he should have 1000 points for point scale *Posts*

