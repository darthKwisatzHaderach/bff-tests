package enums;

public enum UrlPattern {
    PRODUCT("/v1/product"),
    INFO("/info"),
    PRICE("/price"),
    STOCK("/stock");

    public final String pattern;

    private UrlPattern(String pattern) {
        this.pattern = pattern;
    }
}
