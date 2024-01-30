Feature: people endpoints

  Scenario Outline: get people details
    Given Following people are persisted
      | personId | personName | personAge | hatId |
      | 1        | Tom        | 18        | 1     |
      | 2        | Jerry      | 15        | 2     |
    When Client requests person <personId> details
    Then Client gets a person with identifier <personId>, name '<personName>', age <personAge> and hat with identifier <hatId>, name '<hatName>', size <hatSize> and color '<hatColor>'

    Examples:
    | personId  | personName  | personAge | hatId | hatName     | hatSize | hatColor  |
    | 1         | Tom         | 18        | 1     | Test Hat 1  | 10      | striped   |
    | 2         | Jerry       | 15        | 2     | Test Hat 2  | 7       | green     |

  Scenario: get person 1 details
    Given Person with identifier 1, name 'Tom', age 18 and hat with identifier 1 is persisted
    And Client requests person 1 details
    Then Client gets a 200 http status
    And Client gets a person with identifier 1, name 'Tom', age 18 and hat with identifier 1, name 'Test Hat 1', size 10 and color 'striped'

  Scenario: get person 2 details
    Given Person with identifier 2, name 'Jerry', age 15 and hat with identifier 2 is persisted
    And Client requests person 2 details
    Then Client gets a 200 http status
    And Client gets a person with identifier 2, name 'Jerry', age 15 and hat with identifier 2, name 'Test Hat 2', size 7 and color 'green'

  Scenario: get person 3 details
    When Client requests person 3 details
    Then Client gets a 404 http status