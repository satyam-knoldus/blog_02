package com.knolx.calcite

import org.apache.calcite.jdbc.CalciteSchema
import org.apache.calcite.rel.`type`.{RelDataType, RelDataTypeFactory}
import org.apache.calcite.schema.impl.AbstractTable
import org.apache.calcite.sql.`type`.SqlTypeName

object Schema {
  def createSampleSchema(rootSchema: CalciteSchema): CalciteSchema = {
    rootSchema.add(
      "SAMPLE",
      new AbstractTable() {
        override def getRowType(
                                 typeFactory: RelDataTypeFactory
                               ): RelDataType = {
          val builder = typeFactory.builder
          val id = typeFactory.createTypeWithNullability(
            typeFactory.createSqlType(SqlTypeName.INTEGER),
            false
          )
          val name = typeFactory.createTypeWithNullability(
            typeFactory.createSqlType(SqlTypeName.VARCHAR),
            false
          )
          builder.add("ID", id)
          builder.add("NAME", name)
          builder.build
        }
      }
    )
    rootSchema
  }

  def registerType(rootSchema: CalciteSchema): CalciteSchema = {
    rootSchema.add("MY_CUSTOM_TYPE",
      (typeFactory: RelDataTypeFactory) => typeFactory.createSqlType(SqlTypeName.VARCHAR)
    )
    rootSchema
  }
}