<div style="height:6px;"></div>
<div style="text-align:center"><b>课程分表(<@code codeType='Term' data=termId />)</b></div>
<div style="height:12px;"></div>
<table border="1" cellpadding="0" cellspacing="0" width="100%">
	<thead>
		<tr>
			<th style="text-align:center;" rowspan="2">课程</th>
			<th style="text-align:center;" rowspan="2">课时</th>
			<th style="text-align:center;" rowspan="2">单双</th>
			<th style="text-align:center;" rowspan="1" colspan="3">周一</th>
			<th style="text-align:center;" rowspan="1" colspan="3">周二</th>
			<th style="text-align:center;" rowspan="1" colspan="3">周三</th>
			<th style="text-align:center;" rowspan="1" colspan="3">周四</th>
			<th style="text-align:center;" rowspan="1" colspan="3">周五</th>
		</tr>
		<tr>
			<th style="text-align:center;">教师</th>
			<th style="text-align:center;">班级</th>
			<th style="text-align:center;">教室</th>
			<th style="text-align:center;">教师</th>
			<th style="text-align:center;">班级</th>
			<th style="text-align:center;">教室</th>
			<th style="text-align:center;">教师</th>
			<th style="text-align:center;">班级</th>
			<th style="text-align:center;">教室</th>
			<th style="text-align:center;">教师</th>
			<th style="text-align:center;">班级</th>
			<th style="text-align:center;">教室</th>
			<th style="text-align:center;">教师</th>
			<th style="text-align:center;">班级</th>
			<th style="text-align:center;">教室</th>
		</tr>
	</thead>
	<tbody>
		<#list items as item>
		<tr>
			<#if (item_index % 10) == 0>
			<td rowSpan="10"><@code codeType='Course' data=item.course /></td>
			</#if>
			<#if (item_index % 2) == 0>
			<td rowSpan="2"><@code codeType='Lesson' data=item.lesson /></td>
			</#if>
			<td><@code codeType='WeekOeInd' data=item.oeInd /></td>
			<td><@code codeType='Teacher' data=item.monTeacher /></td>
			<td><@code codeType='Class' data=item.monClass/></td>
			<td><@code codeType='Room' data=item.monRoom /></td>
			<td><@code codeType='Teacher' data=item.tueTeacher /></td>
			<td><@code codeType='Class' data=item.tueClass/></td>
			<td><@code codeType='Room' data=item.tueRoom /></td>
			<td><@code codeType='Teacher' data=item.webTeacher /></td>
			<td><@code codeType='Class' data=item.webClass/></td>
			<td><@code codeType='Room' data=item.webRoom /></td>
			<td><@code codeType='Teacher' data=item.thuTeacher /></td>
			<td><@code codeType='Class' data=item.thuClass/></td>
			<td><@code codeType='Room' data=item.thuRoom /></td>
			<td><@code codeType='Teacher' data=item.friTeacher /></td> 
			<td><@code codeType='Class' data=item.friClass/></td>
			<td><@code codeType='Room' data=item.friRoom /></td>
		</tr>
		</#list>
	</tbody>
</table>