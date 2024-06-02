package com.ibm.quarkusacademy.library.model;

import java.time.LocalDateTime;
import java.util.UUID;

/**
 * Availability of physical books in the library
 */
public record BookAvailable(UUID physicalBookId, LocalDateTime availableFrom) {

}
