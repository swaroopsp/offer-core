# BG Microservices port tracker
### This page will provide insight on the DEV/TEST and PROD microservices ports. Please use this for managing the ports in both dev/test and prod environments

## CAAS
<table>
  <thead>
    <tr>
      <th><sub>Microservice name</sub></th>
      <th><sub>TEST/DEV yml config file</sub></th>
      <th><sub>No. <br/>Of <br/>Assigned Ports</sub></th>
      <th><sub>Test env app ports</sub></th>
      <th><sub>Test env management ports</sub></th>
      <th><sub>PROD yml config file</sub></th>
      <th><sub>No. <br/>Of <br/>Assigned <br/>Ports</sub></th>
      <th><sub>PROD env app ports</sub></th>
      <th><sub>PROD env management ports</sub></th>
    </tr>
  </thead>
  <tbody>
    <tr>
      <td><sub>emails</sub></td>
      <td><sub><a href="https://tools.britishgas.co.uk/stash/projects/OA/repos/support-config-test/browse/emails.yml"> emails.yml</sub></td>
      <td><sub>3</sub></td>
      <td><sub>9010 - 9012</sub></td>
      <td><sub>9011 - 9012</sub></td>
      <td><sub><a href="https://tools.britishgas.co.uk/stash/projects/OA/repos/config-prod/browse/emails.yml"> emails.yml</sub></td>
      <td><sub>3</sub></td>
      <td><sub>9050 - 9052</sub></td>
      <td><sub>9150 - 9152</sub></td>
    </tr>
    <tr>
      <td><sub>audits</sub></td>
      <td><sub><a href="https://tools.britishgas.co.uk/stash/projects/OA/repos/support-config-test/browse/audits.yml" > audits.yml</sub></td>
      <td><sub>3</sub></td>
      <td><sub>9070 - 9072</sub></td>
      <td><sub>9170 - 9172</sub></td>
      <td><sub><a href="https://tools.britishgas.co.uk/stash/projects/OA/repos/config-prod/browse/audits.yml" > audits.yml</sub></td>
      <td><sub>3</sub></td>
      <td><sub>9070 - 9072</sub></td>
      <td><sub>9170 - 9172</sub></td>
    </tr>
    <tr>
      <td><sub>addresses</sub></td>
      <td><sub><a href="https://tools.britishgas.co.uk/stash/projects/OA/repos/support-config-test/browse/v1/addresses-v1.yml">addresses.yml</sub></td>
      <td><sub>3</sub></td>
      <td><sub>9040 - 9042</sub></td>
      <td><sub>9140 - 9142</sub></td>
      <td><sub><a href="https://tools.britishgas.co.uk/stash/projects/OA/repos/config-prod/browse/v1/addresses-v1.yml"> addresses.yml</sub></td>
      <td><sub>3</sub></td>
      <td><sub>9040 - 9042</sub></td>
      <td><sub>9140 - 9142</sub></td>
    </tr>
    <tr>
      <td><sub>appointments</sub></td>
      <td><sub><a href="https://tools.britishgas.co.uk/stash/projects/OA/repos/support-config-test/browse/v1/appointments-v1.yml">appointments.yml</sub></td>
      <td><sub>3</sub></td>
      <td><sub>9250 - 9252</sub></td>
      <td><sub>9350 - 9352</sub></td>
      <td><sub><a href="https://tools.britishgas.co.uk/stash/projects/OA/repos/config-prod/browse/v1/appointments-v1.yml"> appointments.yml</sub></td>
      <td><sub>3</sub></td>
      <td><sub>9340 - 9342</sub></td>
      <td><sub>9440, 9441, 9441</sub></td>
    </tr>
    <tr>
      <td><sub>contact</sub></td>
      <td><sub><a href="https://tools.britishgas.co.uk/stash/projects/OA/repos/support-config-test/browse/v1/contact-v1.yml"> contact.yml</sub></td>
      <td><sub>3</sub></td>
      <td><sub>9050 - 9052</sub></td>
      <td><sub>9150 - 9152</sub></td>
      <td><sub><a href"https://tools.britishgas.co.uk/stash/projects/OA/repos/config-prod/browse/v1/contact-v1.yml"> contact.yml</sub></td>
      <td><sub>3</sub></td>
      <td><sub>9300 - 9302</sub></td>
      <td><sub>9400 - 9402</sub></td>
    </tr>
    <tr>
      <td><sub>content</sub></td>
      <td><sub><a href="https://tools.britishgas.co.uk/stash/projects/OA/repos/support-config-test/browse/v1/content-v1.yml"> content.yml</sub></td>
      <td><sub>3</sub></td>
      <td><sub>9090 - 9092</sub></td>
      <td><sub>9190 - 9192</sub></td>
      <td><sub><a href="https://tools.britishgas.co.uk/stash/projects/OA/repos/config-prod/browse/v1/content-v1.yml">content.yml</sub></td>
      <td><sub>3</sub></td>
      <td><sub>9260 - 9262</sub></td>
      <td><sub>9360 - 9362</sub></td>
    </tr>
    <tr>
      <td><sub>customers</sub></td>
      <td><sub><a href="https://tools.britishgas.co.uk/stash/projects/OA/repos/support-config-test/browse/v1/customers-v1.yml"> customers.yml</sub></td>
      <td><sub>3</sub></td>
      <td><sub>9060 - 9062</sub></td>
      <td><sub>9160 - 9162</sub></td>
      <td><sub><a href="https://tools.britishgas.co.uk/stash/projects/OA/repos/config-prod/browse/v1/customers-v1.yml"> customers.yml</sub></td>
      <td><sub>3</sub></td>
      <td><sub>9060 - 9062</sub></td>
      <td><sub>9160 - 9162</sub></td>
    </tr>
    <tr>
      <td><sub>energy</sub></td>
      <td><sub><a href="https://tools.britishgas.co.uk/stash/projects/OA/repos/support-config-test/browse/v1/energy-v1.yml"> energy.yml</sub></td>
      <td><sub>3</sub></td>
      <td><sub>9210 - 9212</sub></td>
      <td><sub>9310 - 9312</sub></td>
      <td><sub><a href="https://tools.britishgas.co.uk/stash/projects/OA/repos/config-prod/browse/v1/energy-v1.yml">energy.yml</sub></td>
      <td><sub>3</sub></td>
      <td><sub>9310 - 9312</sub></td>
      <td><sub>9410 - 9412</sub></td>
    </tr>
    <tr>
      <td><sub>energysales</sub></td>
      <td><sub><a href="https://tools.britishgas.co.uk/stash/projects/OA/repos/support-config-test/browse/v1/energysales-v1.yml"> energysales.yml</sub></td>
      <td><sub>3</sub></td>
      <td><sub>9080 - 9082</sub></td>
      <td><sub>9180 - 9082</sub></td>
      <td><sub><a href="https://tools.britishgas.co.uk/stash/projects/OA/repos/config-prod/browse/v1/energysales-v1.yml">energysales.yml</sub></td>
      <td><sub>3</sub></td>
      <td><sub>9080 - 9082</sub></td>
      <td><sub>9180 - 9182</sub></td>
    </tr>
    <tr>
      <td><sub>home-services</sub></td>
      <td><sub><a href="https://tools.britishgas.co.uk/stash/projects/OA/repos/support-config-test/browse/v1/home-services-v1.yml"> home-services.yml</sub></td>
      <td><sub>2</sub></td>
      <td><sub>9240 - 9241</sub></td>
      <td><sub>9340 - 9341</sub></td>
      <td><sub><a href="https://tools.britishgas.co.uk/stash/projects/OA/repos/config-prod/browse/v1/home-services-v1.yml">home-services.yml</sub></td>
      <td><sub>3</sub></td>
      <td><sub>9240 - 9242</sub></td>
      <td><sub>9540 - 9542</sub></td>
    </tr>
    <tr>
      <td><sub>homemove</sub></td>
      <td><sub><a href="https://tools.britishgas.co.uk/stash/projects/OA/repos/support-config-test/browse/v1/home-move-v1.yml">homemove.yml</sub></td>
      <td><sub>3</sub></td>
      <td><sub>9200 - 9202</sub></td>
      <td><sub>9300 - 9302</sub></td>
      <td><sub><a href="https://tools.britishgas.co.uk/stash/projects/OA/repos/config-prod/browse/v1/home-move-v1.yml"> homemove.yml </sub></td>
      <td><sub>3</sub></td>
      <td><sub>9330 - 9332</sub></td>
      <td><sub>9430 - 9432</sub></td>
    </tr>
        <tr>
          <td><sub>payments</sub></td>
          <td><sub><a href="https://tools.britishgas.co.uk/stash/projects/OA/repos/support-config-test/browse/v1/payments-v1.yml">payments.yml</sub></td>
          <td><sub>3</sub></td>
          <td><sub>9220 - 9222</sub></td>
          <td><sub>9320 - 9322</sub></td>
          <td><sub><a href="https://tools.britishgas.co.uk/stash/projects/OA/repos/config-prod/browse/v1/payments-v1.yml">payments.yml</sub></td>
          <td><sub>3</sub></td>
          <td><sub>9320 - 9322</sub></td>
          <td><sub>9420 - 9422</sub></td>
    </tr>
        <tr>
          <td><sub>questionnaires</sub></td>
          <td><sub><a href="https://tools.britishgas.co.uk/stash/projects/OA/repos/support-config-test/browse/v1/questionnaires-v1.yml"> questionnaires.yml</sub></td>
          <td><sub>3</sub></td>
          <td><sub>9030 - 9032</sub></td>
          <td><sub>9130 - 9132</sub></td>
          <td><sub><a href="https://tools.britishgas.co.uk/stash/projects/OA/repos/config-prod/browse/v1/questionnaires-v1.yml"> questionnaires.yml</sub></td>
          <td><sub>3</sub></td>
          <td><sub>9030 - 9032</sub></td>
          <td><sub>9130 - 9132</sub></td>
    </tr>
        <tr>
          <td><sub>uaa</sub></td>
          <td><sub><a href="https://tools.britishgas.co.uk/stash/projects/OA/repos/support-config-test/browse/uaa.yml">uaa.yml</sub></td>
          <td><sub>3</sub></td>
          <td><sub>9021 - 9023</sub></td>
          <td><sub>9025 - 9027</sub></td>
          <td><sub><a href="https://tools.britishgas.co.uk/stash/projects/OA/repos/config-prod/browse/uaa.yml">uaa.yml</sub></td>
          <td><sub>6</sub></td>
          <td><sub>9101 - 9116</sub></td>
          <td><sub>9111 - 9116</sub></td>
    </tr>
        <tr>
          <td><sub>ukb-core-addresses</sub></td>
          <td><sub><a href="https://tools.britishgas.co.uk/stash/projects/OA/repos/support-config-test/browse/v1/ukb-core-addresses-v1.yml">ukb-core-addresses.yml</sub></td>
          <td><sub>3</sub></td>
          <td><sub>20010 - 20012</sub></td>
          <td><sub>20110 - 20112</sub></td>
          <td><sub><a href="https://tools.britishgas.co.uk/stash/projects/OA/repos/config-prod/browse/v1/ukb-core-addresses-v1.yml">ukb-core-addresses.yml</sub></td>
          <td><sub>2</sub></td>
          <td><sub>20010 - 20012</sub></td>
          <td><sub>20110 - 20112</sub></td>
    </tr>
        <tr>
                  <td><sub>Zuul</sub></td>
                  <td><sub><a href="https://tools.britishgas.co.uk/stash/projects/OA/repos/support-config-test/browse/zuul-server.yml">Zuul.yml </sub></td>
                  <td><sub>3</sub></td>
                  <td><sub>8771, 8773, 8775</sub></td>
                  <td><sub>8875 - 8877</sub></td>
                  <td><sub><a href="https://tools.britishgas.co.uk/stash/projects/OA/repos/config-prod/browse/zuul-server.yml">Zuul.yml</sub></td>
                  <td><sub>6</sub></td>
                  <td><sub>9201 - 9206</sub></td>
                  <td><sub>9211 - 9216</sub></td>
    </tr>
    <tr>
              <td><sub>users</sub></td>
              <td><sub><a href="https://tools.britishgas.co.uk/stash/projects/OA/repos/support-config-test/browse/v1/users-v1.yml">users.yml</sub></td>
              <td><sub>3</sub></td>
              <td><sub>9260 - 9262</sub></td>
              <td><sub>9360 - 9362</sub></td>
              <td><sub><i>TO BE RELEASED IN PROD</i></sub></td>
              <td><sub><i>TO BE RELEASED IN PROD</i></sub></td>
              <td><sub><i>TO BE RELEASED IN PROD</i></sub></td>
              <td><sub><i>TO BE RELEASED IN PROD</i></sub></td>
    </tr>
    	<tr>
      	  <td><sub>meters</sub></td>
    	  <td><sub><a href="https://tools.britishgas.co.uk/stash/projects/OA/repos/support-config-test/browse/v1/meters-v1.yml"> meters.yml</sub></td>
    	  <td><sub>2</sub></td>
    	  <td><sub>9230 - 9231</sub></td>
    	  <td><sub>9330 - 9331</sub></td>
    	  <td><sub><i>TO BE RELEASED IN PROD</sub></td>
    	  <td><sub><i>TO BE RELEASED IN PROD</sub></td>
    	  <td><sub><i>TO BE RELEASED IN PROD</sub></td>
    	  <td><sub><i>TO BE RELEASED IN PROD</sub></td>
    </tr>
  </tbody>
</table>