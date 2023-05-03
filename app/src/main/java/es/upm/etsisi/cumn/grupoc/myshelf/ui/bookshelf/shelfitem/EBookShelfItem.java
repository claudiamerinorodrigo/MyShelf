package es.upm.etsisi.cumn.grupoc.myshelf.ui.bookshelf.shelfitem;

public enum EBookShelfItem {
    TO_READ("PARA LEER"),
    READ("LEIDOS");

    private String display_name;
    EBookShelfItem(String display_name) {
        this.display_name = display_name;
    }

    public String getDisplayName() {
        return display_name;
    }
}
