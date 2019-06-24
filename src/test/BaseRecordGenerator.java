//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package org.mybatis.generator.codegen.mybatis3.model;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.mybatis.generator.api.CommentGenerator;
import org.mybatis.generator.api.FullyQualifiedTable;
import org.mybatis.generator.api.IntrospectedColumn;
import org.mybatis.generator.api.Plugin;
import org.mybatis.generator.api.Plugin.ModelClassType;
import org.mybatis.generator.api.dom.java.CompilationUnit;
import org.mybatis.generator.api.dom.java.Field;
import org.mybatis.generator.api.dom.java.FullyQualifiedJavaType;
import org.mybatis.generator.api.dom.java.JavaVisibility;
import org.mybatis.generator.api.dom.java.Method;
import org.mybatis.generator.api.dom.java.Parameter;
import org.mybatis.generator.api.dom.java.TopLevelClass;
import org.mybatis.generator.codegen.AbstractJavaGenerator;
import org.mybatis.generator.codegen.RootClassInfo;
import org.mybatis.generator.internal.util.JavaBeansUtil;
import org.mybatis.generator.internal.util.messages.Messages;

public class BaseRecordGenerator extends AbstractJavaGenerator {
    public BaseRecordGenerator() {
    }

    public List<CompilationUnit> getCompilationUnits() {
        FullyQualifiedTable var1 = this.introspectedTable.getFullyQualifiedTable();
        this.progressCallback.startTask(Messages.getString("Progress.8", var1.toString()));
        Plugin var2 = this.context.getPlugins();
        CommentGenerator var3 = this.context.getCommentGenerator();
        FullyQualifiedJavaType var4 = new FullyQualifiedJavaType(this.introspectedTable.getBaseRecordType());
        TopLevelClass var5 = new TopLevelClass(var4);
        var5.addSuperInterface(new FullyQualifiedJavaType("Serializable"));
        var5.addImportedType("java.io.Serializable");
        var5.setSuperClass("DataEntity<" + var4.getShortName() + ">");
        var5.setVisibility(JavaVisibility.PUBLIC);
        var3.addJavaFileComment(var5);
        FullyQualifiedJavaType var6 = this.getSuperClass();
        if (var6 != null) {
            var5.setSuperClass(var6);
            var5.addImportedType(var6);
        }

        var3.addModelClassComment(var5, this.introspectedTable);
        List var7 = this.getColumnsInThisClass();
        if (this.introspectedTable.isConstructorBased()) {
            this.addParameterizedConstructor(var5);
            if (!this.introspectedTable.isImmutable()) {
                this.addDefaultConstructor(var5);
            }
        }

        String var8 = this.getRootClass();
        Iterator var9 = var7.iterator();

        while(var9.hasNext()) {
            IntrospectedColumn var10 = (IntrospectedColumn)var9.next();
            if (!RootClassInfo.getInstance(var8, this.warnings).containsProperty(var10)) {
                Field var11 = JavaBeansUtil.getJavaBeansField(var10, this.context, this.introspectedTable);
                if (var2.modelFieldGenerated(var11, var5, var10, this.introspectedTable, ModelClassType.BASE_RECORD)) {
                    var5.addField(var11);
                    var5.addImportedType(var11.getType());
                }

                Method var12 = JavaBeansUtil.getJavaBeansGetter(var10, this.context, this.introspectedTable);
                if (var2.modelGetterMethodGenerated(var12, var5, var10, this.introspectedTable, ModelClassType.BASE_RECORD)) {
                    var5.addMethod(var12);
                }

                if (!this.introspectedTable.isImmutable()) {
                    var12 = JavaBeansUtil.getJavaBeansSetter(var10, this.context, this.introspectedTable);
                    if (var2.modelSetterMethodGenerated(var12, var5, var10, this.introspectedTable, ModelClassType.BASE_RECORD)) {
                        var5.addMethod(var12);
                    }
                }
            }
        }

        ArrayList var13 = new ArrayList();
        if (this.context.getPlugins().modelBaseRecordClassGenerated(var5, this.introspectedTable)) {
            var13.add(var5);
        }

        return var13;
    }

    private FullyQualifiedJavaType getSuperClass() {
        FullyQualifiedJavaType var1;
        if (this.introspectedTable.getRules().generatePrimaryKeyClass()) {
            var1 = new FullyQualifiedJavaType(this.introspectedTable.getPrimaryKeyType());
        } else {
            String var2 = this.getRootClass();
            if (var2 != null) {
                var1 = new FullyQualifiedJavaType(var2);
            } else {
                var1 = null;
            }
        }

        return var1;
    }

    private boolean includePrimaryKeyColumns() {
        return !this.introspectedTable.getRules().generatePrimaryKeyClass() && this.introspectedTable.hasPrimaryKeyColumns();
    }

    private boolean includeBLOBColumns() {
        return !this.introspectedTable.getRules().generateRecordWithBLOBsClass() && this.introspectedTable.hasBLOBColumns();
    }

    private void addParameterizedConstructor(TopLevelClass var1) {
        Method var2 = new Method();
        var2.setVisibility(JavaVisibility.PUBLIC);
        var2.setConstructor(true);
        var2.setName(var1.getType().getShortName());
        this.context.getCommentGenerator().addGeneralMethodComment(var2, this.introspectedTable);
        List var3 = this.includeBLOBColumns() ? this.introspectedTable.getAllColumns() : this.introspectedTable.getNonBLOBColumns();
        Iterator var4 = var3.iterator();

        IntrospectedColumn var5;
        while(var4.hasNext()) {
            var5 = (IntrospectedColumn)var4.next();
            var2.addParameter(new Parameter(var5.getFullyQualifiedJavaType(), var5.getJavaProperty()));
            var1.addImportedType(var5.getFullyQualifiedJavaType());
        }

        StringBuilder var6 = new StringBuilder();
        Iterator var7;
        if (this.introspectedTable.getRules().generatePrimaryKeyClass()) {
            boolean var8 = false;
            var6.append("super(");

            for(var7 = this.introspectedTable.getPrimaryKeyColumns().iterator(); var7.hasNext(); var6.append(var5.getJavaProperty())) {
                var5 = (IntrospectedColumn)var7.next();
                if (var8) {
                    var6.append(", ");
                } else {
                    var8 = true;
                }
            }

            var6.append(");");
            var2.addBodyLine(var6.toString());
        }

        List var9 = this.getColumnsInThisClass();
        var7 = var9.iterator();

        while(var7.hasNext()) {
            var5 = (IntrospectedColumn)var7.next();
            var6.setLength(0);
            var6.append("this.");
            var6.append(var5.getJavaProperty());
            var6.append(" = ");
            var6.append(var5.getJavaProperty());
            var6.append(';');
            var2.addBodyLine(var6.toString());
        }

        var1.addMethod(var2);
    }

    private List<IntrospectedColumn> getColumnsInThisClass() {
        List var1;
        if (this.includePrimaryKeyColumns()) {
            if (this.includeBLOBColumns()) {
                var1 = this.introspectedTable.getAllColumns();
            } else {
                var1 = this.introspectedTable.getNonBLOBColumns();
            }
        } else if (this.includeBLOBColumns()) {
            var1 = this.introspectedTable.getNonPrimaryKeyColumns();
        } else {
            var1 = this.introspectedTable.getBaseColumns();
        }

        return var1;
    }
}
