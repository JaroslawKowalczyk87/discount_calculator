# discount_calculator

### Building the app
App can be simply copied from GitHub (in IntelliJ: File -> New -> Project from Version Control...).
src directory should be marked as the sources root.
test directory should be marked as the test sources root.

### Starting up the app
You can start the app by running the Main function (src.main.Main) in IntelliJ IDEA or any other IDE.

### Interacting with the app
To get the summary of price and discounts, you have to type the list of products preceded by "PriceBasket".
For example:

`PriceBasket Apples Apples Soup Bread`

If the input doesn't start with 'PriceBasket', it will be ignored by the app and the user will be prompted for another input.
Product names are case-insensitive.

### Shutting down the app
To shut down the app, just put
`stop`
as the input.