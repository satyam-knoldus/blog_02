package com.knolx.calcite

import com.knolx.calcite.Schema.registerType
import org.apache.calcite.jdbc.CalciteSchema
import org.apache.calcite.plan.RelOptUtil
import org.apache.calcite.sql.fun.SqlStdOperatorTable
import org.apache.calcite.sql.util.SqlOperatorTables
import org.apache.calcite.tools.Frameworks

object Main extends App {

  val config = Frameworks.newConfigBuilder()
    .defaultSchema(
      Schema.createSampleSchema(
        registerType(CalciteSchema.createRootSchema(false, false))
      )
        .plus()
    )
    .operatorTable(SqlOperatorTables.chain(
      SqlStdOperatorTable.instance(),
      OperatorTable.getCustomTable
    ))
    .build()

  val planner = Frameworks.getPlanner(config)
  val sqlNode = planner.parse(
    "SELECT CAST(my_custom_function(name) as my_custom_type)  FROM SAMPLE"
  )
  val validatedNode = planner.validate(sqlNode)
  val relNode = planner.rel(validatedNode)

  println(RelOptUtil.toString(relNode.rel))

}
