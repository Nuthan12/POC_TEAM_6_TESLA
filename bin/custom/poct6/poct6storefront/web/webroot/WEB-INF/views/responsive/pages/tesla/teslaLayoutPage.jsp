




<%@ page trimDirectiveWhitespaces="true"%>

<%@ taglib prefix="template" tagdir="/WEB-INF/tags/responsive/template"%>

<%@ taglib prefix="cms" uri="http://hybris.com/tld/cmstags"%>

 

<template:page pageTitle="${pageTitle}">

        <cms:pageSlot position="teslaParagraph" var="feature"
		class="teslaBannerParagraph">
		
		<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Model S</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-aFq/bzH65dt+w6FI2ooMVUpc+21e0SRygnTpmBvdBgSdnuTN7QbdgL+OapgHtvPp" crossorigin="anonymous">
  <link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link href="https://fonts.googleapis.com/css2?family=Orbitron:wght@400;500;600;700;800;900&display=swap" rel="stylesheet">
  <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha2/dist/js/bootstrap.bundle.min.js" integrity="sha384-qKXV1j0HvMUeCBQ+QVp7JcfGl760yU08IQ+GpUo5hlbpg51QRiuqHAJz8+BrxE/N" crossorigin="anonymous"></script>
    <style>
      .desc-tbl {
        color: black;
        /* text-align: center; */
        margin-left: auto;
        margin-right: auto;
        /* width: 60%; */
      }
      .button-desc {
        border: none;
        background-color: black;
        border: 3px solid transparent;
        color: white;
        font-size: 1rem;
        padding: 0.5rem 1rem;
        margin: 0.5rem;
        cursor: pointer;
        transition: all 0.3s ease;
        width: 150px;
      }
    </style>
  </head>
  <body>
    <div class="row models">
      <div class="col-md-12 col-lg-8" style="margin-top: 7rem;">
        <div id="myCarousel" class="carousel carousel-dark slide" data-bs-ride="carousel">
          <div class="carousel-inner">
            <div class="carousel-item active">
              <img src="https://tesla-cdn.thron.com/delivery/public/image/tesla/9ed31f26-4d49-4701-bc5b-2121ff85e5b3/bvlatuR/std/2880x1800/Model-S-Main-Hero-Desktop-LHD?quality=auto-medium&amp;format=auto" class="d-block w-100" alt="Your Image 1" />
            </div>
          </div>
          <button class="carousel-control-prev" type="button" data-bs-target="#myCarousel" data-bs-slide="prev">
            <span class="carousel-control-prev-icon" aria-hidden="true"></span>
            <span class="visually-hidden">Previous</span>
          </button>
          <button class="carousel-control-next" type="button" data-bs-target="#myCarousel" data-bs-slide="next">
            <span class="carousel-control-next-icon" aria-hidden="true"></span>
            <span class="visually-hidden">Next</span>
          </button>
        </div>
      </div>
      <div class="col-lg-4 col-md-12" style="max-height: 710px; overflow-y: scroll; margin-top: 4.5rem; text-align: center;">
        <h1>Model S</h1>
        <h6>Model S Dual Motor All-Wheel Drive</h6>
        <br />
        <table class="desc-tbl">
          <tr class="thead">
            <th><h4><b>365 mi</b></h4></th>
            <th><h4><b>149 mph</b></h4></th>
            <th><h4><b>3.1 s</b></h4></th>
          </tr>
          <tr>
            <td>Range(EPA est.)</td>
            <td>Top Speed</td>
            <td>0-60 mph</td>
</tr>
</table>
<br />
<h2>Key Features</h2>
<br />
<ul style="text-align:left; width:50%; margin-left:auto; margin-right:auto">
<li>Ultra Red</li>
<li>21" Arachnid Wheels</li>
<li>Black and White Premium Interior with Walnut Décor</li>
<li>Yoke Steering</li>
<li>Autopilot</li>
<li>1-Year Premium Connectivity Trial</li>

</ul>
<br/>
<h2> Enhanced Autopilot</h2>
<p>$6,000</p>
<ul style="text-align:justify; width:50%;margin-left:auto;margin-right:auto">
<li>Automatic steering, acceleration and braking on highways
  <br />
  <br />
  The currently enabled features require active driver supervision and do not make the vehicle autonomous. The activation and use of these features are dependent on achieving reliability far in excess of human drivers as demonstrated by billions of miles of experience, as well as regulatory approval, which may take longer in some jurisdictions. As these self-driving features evolve, your car will be continuously upgraded through over-the-air software updates.
</li>
</ul>
<br />
<div style="width:50%;margin-left:auto;margin-right:auto">
<h2>Warranty</h2>
<br />
<h6>Basic Vehicle Limited Warranty</h6>
<p>December 2023 / 50,000 total miles</p>

<h6>Used Vehicle Limited Warranty</h6>
<text>Additional 1 year / 10,000 miles</text>
<text>Begins after Basic Vehicle Limited Warranty expires</text>
<br />
<br />
<h6>Battery and Drive Unit Limited Warranty</h6>
<p>December 2027 / unlimited mileage</p>
<br />
<a href="https://apparel-uk.local:9002/poct6storefront/en/login/checkout"><button className="btn btn-secondary btn-lg" onclick="openPage('checkoutGuestLogin.jsp.jsp')">Book a test drive</button></a>
</div>
</div>
    </div>
           
		

            <cms:component component="${feature}" />

        </cms:pageSlot>

        

 

</template:page>
       


