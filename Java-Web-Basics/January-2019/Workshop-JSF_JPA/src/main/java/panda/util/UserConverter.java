package panda.util;

import panda.domain.entities.User;
import panda.web.mbeans.CreatePackageBean;

import javax.el.ValueExpression;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

@FacesConverter(value = "userConverter")
public class UserConverter implements Converter {

    @Override
    public Object getAsObject(FacesContext ctx, UIComponent uiComponent, String userTo) {
        ValueExpression vex =
                ctx.getApplication().getExpressionFactory()
                        .createValueExpression(ctx.getELContext(),
                                "#{createPackageBean}", CreatePackageBean.class);

        CreatePackageBean packages = (CreatePackageBean)vex.getValue(ctx.getELContext());
        return packages.getUser(userTo);
    }

    @Override
    public String getAsString(FacesContext facesContext, UIComponent uiComponent, Object user) {
        return user.toString();
    }

}