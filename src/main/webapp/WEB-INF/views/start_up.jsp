<form method="POST" action="/first_point/" autocomplete="off">
	<fieldset class="floating-box">
		<legend>Pocetno teme i broj temena</legend>
		<table>
			<tr>
				<td>Unesi broj pocetnog temena T </td>
				<td><input type="text" name="startPoint" value="${entry.startPoint}" size="5"/></td>
			</tr>
			<tr>
				<td>Unesi ukupan broj temena T (max 8) </td>
				<td><input type="text" name="totalNumber" value="${entry.totalNumber}" size="5"/></td>
			</tr>
			<tr>
				<td colspan="1">
					<input type="submit" name="buttonAction" value="Nastavi" class="button" />
				</td>
			</tr>
		</table>
	</fieldset>
</form>
