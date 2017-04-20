ALTER TABLE `concierge`.`hotel`
ADD check_in TIME(0) NOT NULL AFTER description,
ADD check_out TIME(0) NOT NULL AFTER check_in;