package sharingcalender.front.dto.calendar.request;

public record EventColorChangeRequestDto (
    long eventId,
    String color
){}
