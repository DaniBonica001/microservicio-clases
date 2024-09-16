CREATE TABLE gymclasses (
                             classId BIGINT AUTO_INCREMENT PRIMARY KEY,
                             name VARCHAR(255),
                             schedule TIMESTAMP,
                             maxCapacity INT NOT NULL,
                             trainerId BIGINT
);