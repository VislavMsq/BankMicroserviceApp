package bankmicroservicesapp.entity.enums;

public enum StatusAccount {
    New, //        5
    Active, //                  6
    Blocked, // -1
    Frozen, // -
    Closed, // -
    ActiveWithRestrictions, //  1
    Privileged, // 7
    WithDebt, // 2
    doubtful, // сомнительный | 3
    Temporary, // 5
    Premium, // 9
    Reserved, // 5
    HighCreditRate, // 8
    LowCreditRate //  4
}
