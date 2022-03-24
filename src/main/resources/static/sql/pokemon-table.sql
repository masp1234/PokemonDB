CREATE TABLE `pokemon` (
                           `pokedex_number` int NOT NULL,
                           `attack` int NOT NULL,
                           `defence` int NOT NULL,
                           `hp` int NOT NULL,
                           `name` varchar(255) DEFAULT NULL,
                           `primary_type` varchar(255) DEFAULT NULL,
                           `secondary_type` varchar(255) DEFAULT NULL,
                           `special_attack` int NOT NULL,
                           `special_defence` int NOT NULL,
                           `speed` int NOT NULL,
                           PRIMARY KEY (`pokedex_number`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci