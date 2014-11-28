You must add the bttelco certificate to the java keystore.

Navigate to bttelco.com then export the certificate using your browser.

then you can use the cli tool keytool to import the certificate like this:
keytool -import -keystore cacerts -file bttelco.bittrust.net 
where cacerts is your keystore name (it will create one with this name if you don't have any)
and bttelco.bittrust.net is the file containing the certificate.