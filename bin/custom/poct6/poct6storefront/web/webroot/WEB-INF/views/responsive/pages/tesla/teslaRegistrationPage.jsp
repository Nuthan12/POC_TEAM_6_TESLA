<%@ page trimDirectiveWhitespaces="true" %>

<%@ taglib prefix="template" tagdir="/WEB-INF/tags/responsive/template"%>

<%@ taglib prefix="cms" uri="http://hybris.com/tld/cmstags" %>

 

<template:page pageTitle="${pageTitle}">

        <cms:pageSlot position="teslaParagraph" var="feature" class="teslaBannerParagraph">

            <cms:component component="${feature}" />

        </cms:pageSlot>

        
<head>
    <title>Example Form</title><script>var stateObject = {
      "Karnataka": {
        "Bengaluru": ["Vasanthanagar", "J.P. Nagar", "B.T.M Layout", "Shantinagar","Malleshwaram"],
        "Mysuru": ["MysA", "MysB", "MysC", "MysB"],
           
      },
      "Tamilnadu": {
        "Chennai": ["T Nagar", "Egmore", "Annangar"],
        
      },
      "Maharashtra": {
        "Mumbai": ["a1", "a2", "a3"],
        
      },
      "Westbengal": {
        "Kolkata": ["k1", "k2", "k3"],
       
      }
    }
    window.onload = function() {
      var stateSel = document.getElementById("state");
      var citySel = document.getElementById("city");
      var dealerSel = document.getElementById("dealer");
      for (var x in stateObject) {
        stateSel.options[stateSel.options.length] = new Option(x, x);
      }
      stateSel.onchange = function() {
        //empty dealers- and citys- dropdowns
        dealerSel.length = 1;
        citySel.length = 1;
        //display correct values
        for (var y in stateObject[this.value]) {
          citySel.options[citySel.options.length] = new Option(y, y);
        }
      }
      citySel.onchange = function() {
        //empty dealers dropdown
        dealerSel.length = 1;
        //display correct values
        var z = stateObject[stateSel.value][this.value];
        for (var i = 0; i < z.length; i++) {
          dealerSel.options[dealerSel.options.length] = new Option(z[i], z[i]);
        }
      }
    }
    </script>
    <style>
      /* Internal CSS */
      form {
        margin: 20px;
        padding: 20px;
        border: 1px solid black;
      }
      label {
        display: block;
        margin-bottom: 10px;
        font-weight: bold;
      }
      input[type="text"], textarea {
        width: 100%;
        padding: 5px;
        margin-bottom: 10px;
        border-radius: 5px;
        border: 1px solid #ccc;
      }
      input[type="submit"] {
        background-color: #4CAF50;
        color: white;
        border: none;
        padding: 10px 20px;
        text-align: center;
        text-decoration: none;
        display: inline-block;
        font-size: 16px;
        border-radius: 5px;
        cursor: pointer;
      }

    </style>
  </head>
  <body>
    <form>
      <label for="name">Name:</label>
      <input type="text" id="name" name="name" required>
      
      <label for="email">Email:</label>
      <input type="text" id="email" name="email" required>
      
      <label for="phno">Phone Number:</label>
      <input type="tel" id="phno" name="phno" required>

      <label for="cars">Choose a car:</label>

<select name="model" id="model">
  <option value="s">MODEL S</option>
  <option value="3">MODEL 3</option>
  <option value="x">MODEL X</option>
  <option value="7">MODEL Y</option>
</select>
<br><br>

<select name="timeslot" id="ts">
  <option value="ts1">10:00 am</option>
  <option value="ts2">12:00 pm</option>
  <option value="ts3">2:00 pm</option>
  <option value="ts4">4:00 pm</option>
</select>
<br><br>

<form name="form1" id="form1" action="/action_page.php">
 State <select name="state" id="state">
      <option value="" selected="selected">Select state</option>
    </select>
    <br><br>
  Cities <select name="city" id="city">
      <option value="" selected="selected">Select the nearest dealer</option>
    </select>
    <br><br>
    Dealers associated with tesla: <select name="dealer" id="dealer">
      <option value="" selected="selected">Please select topic first</option>
    </select>
    <br><br>
   

      
      <input type="submit" value="Submit">
    </form>
  </body>
 

</template:page>   