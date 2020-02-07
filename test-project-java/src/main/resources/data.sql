CREATE TABLE IF NOT EXISTS history
(
    id            SERIAL PRIMARY KEY NOT NULL,
    startCurrency VARCHAR(10)        NOT NULL,
    endCurrency   VARCHAR(10)        NOT NULL,
    startAmount   DOUBLE PRECISION   NOT NULL,
    endAmount     DOUBLE PRECISION   NOT NULL,
    date          DATE               NOT NULL DEFAULT CURRENT_DATE
);

CREATE TABLE IF NOT EXISTS currency
(
    id       VARCHAR(10) PRIMARY KEY NOT NULL,
    name     VARCHAR(100)            NOT NULL,
    charCode VARCHAR(10)             NOT NULL,
    nominal  INT                     NOT NULL,
    value    DOUBLE PRECISION        NOT NULL
);