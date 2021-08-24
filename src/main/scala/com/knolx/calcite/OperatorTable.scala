package com.knolx.calcite

import org.apache.calcite.sql.`type`.{OperandTypes, ReturnTypes}
import org.apache.calcite.sql.util.ListSqlOperatorTable
import org.apache.calcite.sql.{SqlFunction, SqlFunctionCategory, SqlKind, SqlOperator}

import scala.jdk.CollectionConverters.SeqHasAsJava

object OperatorTable {

  private val my_custom_function: SqlOperator = new SqlFunction(
    "MY_CUSTOM_FUNCTION",
    SqlKind.OTHER_FUNCTION,
    ReturnTypes.INTEGER,
    null,
    OperandTypes.STRING,
    SqlFunctionCategory.USER_DEFINED_FUNCTION
  )

  def getCustomTable = new ListSqlOperatorTable(
    List(my_custom_function).asJava
  )

}
