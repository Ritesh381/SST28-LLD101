public class RoomPricing implements MonthlyContribution {
    private final int roomType;

    public RoomPricing(int roomType) {
        this.roomType = roomType;
    }

    @Override
    public Money contribute() {
        double base = switch (roomType) {
            case LegacyRoomTypes.SINGLE -> 14000.0;
            case LegacyRoomTypes.DOUBLE -> 15000.0;
            case LegacyRoomTypes.TRIPLE -> 12000.0;
            default -> 16000.0;
        };
        return new Money(base);
    }
}
