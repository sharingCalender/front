package sharingcalender.front.dto.calendar.request;


import java.time.LocalDateTime;

public record EventDto (
    Long id,
    String name,
    String title,
    LocalDateTime start,
    LocalDateTime end,
    String backgroundColor,
    String borderColor,
    String description
){}
