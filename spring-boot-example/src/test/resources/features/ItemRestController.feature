Feature: Item Rest Controller

Scenario Outline: The client add an Item successfully.
	  Given the Item with id=<ID> and description=<DESCRIPTION>
    When the client sends the item to <ENDPOINT> endPoint
    Then the client receives status code of <ERRORCODE>
    
    Examples:
		| ID | DESCRIPTION   | ENDPOINT          | ERRORCODE |
		| 1  | one           | /rest/item/add    | 200       |
		| 2  | two           | /rest/item/add    | 200       |
		| 3  | three         | /rest/item/add    | 200       |
		| 1  | add one again | /rest/item/add    | 403       |