package models.labels;

import models.shampoos.BasicShampoo;

public interface Label {

    public Long getId();

    public void setId(Long id);

    public String getSubtitle();

    public void setSubtitle(String subtitle);

    public String getTitle();

    public void setTitle(String title);

    public BasicShampoo getBasicShampoo();

    public void setBasicShampoo(BasicShampoo basicShampoo);
}
