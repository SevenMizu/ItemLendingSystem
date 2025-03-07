# Stuff Lending Test Report
Document the results of your final system test below. You find instructions on the course homepage.

Group members:
1. Yusuf Awass (@ya222gp)
2. Hsin-Yu Chao (@hc222ir)

Test Results

| Case  | Result  | Note               |
|-------|---------|--------------------|
| 5.1   | [not ok]|                    |
| 1.1   | [ok]    |                    |
| 1.2   | [ok]    |                    |
| 1.3   | [ok]    |                    |
| 2.1   | [ok]    |                    |
| 2.2   | [ok]    |                    |
| 2.3   | [ok]    |                    |
| 3.1   | [ok]    |                    |
| 3.2   | [ok]    |                    |
| 3.3   | [not ok]|                    |
| 4.1   | [not ok]|                    |


Member Management Tests

1.1: Create Member
Test the creation of a new member and verifies if it's added successfully.
   -Create a new member with valid details.
-The member is created and added successfully.

1.2: Create Member - Duplicate Email and Phone
Test the creation of a new member will not work if the member Email or Phone number are has duplicates.
   -Create a new member with valid details.
   -Then create another member with duplicated email or phone number.
-The member is not going to be created when the email or phone numbers are duplicated.

1.3: Delete Member
Test the member delete function.
   -Create a new member with valid details. 
   -Delet the member and check on the member list.
   -Create another new member with the same valid details.
-After delete, the member is not on the list, and the details are able to use for create another member.

2.1 Create item
Create an item for a Member
   -Create an item for a member.
   -Check the item is added to the member's detail.
   -Check the member has gain 100 credits.
-The item is in the member's detail since the item is created, and member got 100 credits after that.

2.2 Delete item
Delete an item from a Member.
   -Delete an item from a member who has several items.
   -Check the item was deleted from the member's owned items.
-The item was removed from the member's owned item list after it was deleted.

2.3 Delet item
Delete an item that is involved in a contract.
   -Delete an item that pacticipate in a contract.
   -Check if the item has been removed from the item list.
-When deleting an item in a contract, we can find that the item has been removed from the list while checking by view all items.

3.1 Created Contract
Create a contract for lending
-The contract was created for item lending to other members.

3.2 Create Contract - not enough funds
Contract not able to be created when there is no enough funds.
-When the cost is larger than the funds, then the item is not allowed the borrow and lend.
-Hence, the contract is not able to be created.

3.3 Create Contract - conflicting time
Check that the contract was not created due to conflicting time.
-Due to the disability of the code itself, the program is not able to create contract when there is time conflicts.

4.1 Advance time
Advance time for 8 days, check if the funds have been deduced or not.
-The function for advance time works correctly, but the algorithm of fund reduce is having problems so that the funds counldn't change after 8 days.

5.1 Member data
Giving three members in the data handler, and there are items for lending. However, we can only adding the member credits by creating items for them, need to figure out a way to be able to set the credits. And there is also so points need to be fixed such as make the starting day attribute be uesd in the algorithm and function that makes the code run better.
