package stepDefinitions;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.testng.Assert;

import hooks.TestContext;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import utilities.Log;
import utilities.ReadConfig;
import utilities.RunTimeData;

public class BatchStepDef {

	private TestContext context;
    private ReadConfig readConfig;

    private List<String> deleteSuccessMessage;

 // Constructor accepts TestContext (for dependency injection via PicoContainer)
    public BatchStepDef(TestContext context) {
        this.context = context;
        this.readConfig = new ReadConfig();
  
    }

	@Then("Admin should be in the Manage Batch Page")
	public void admin_should_be_in_the_manage_batch_page() {
		Log.logInfo("Admin is on ManageBatch page");
		 Assert.assertEquals(context.getBatchPage().getManageBatchText(), "Manage Batch");
		 }

	@Then("Admin should see the {string} Title")
	public void admin_should_see_the_title(String batchTitle) {
		Assert.assertEquals(context.getBatchPage().getBatchPageTitle(), batchTitle);
	}

	@Then("Admin should see the {string} Heading")
	public void admin_should_see_the_heading(String batchPageTitle) {
		Assert.assertEquals(context.getBatchPage().getManageBatchText(), batchPageTitle);
	}

	@Then("Admin should see the disabled {string} under the header")
	public void admin_should_see_the_disabled_under_the_header(String string) {
		Assert.assertEquals(context.getBatchPage().deleteBatchHeaderButton(), false);
	}

	@Then("Admin should see the enabled pagination controls under the data table")
	public void admin_should_see_the_enabled_pagination_controls_under_the_data_table() {
		Assert.assertEquals(context.getBatchPage().isPaginationAvailable(), true);
	}

	@Then("Admin should see the edit icon in each row")
	public void admin_should_see_the_edit_icon_in_each_row() {
		Assert.assertEquals(context.getCommonPage().isElementPresent("edit-icon", "row"), true);

	}

	@Then("Admin should see the sort icon next to all Datatable headers")
	public void admin_should_see_the_sort_icon_next_to_all_datatable_headers() {
		Assert.assertEquals(context.getCommonPage().isElementPresent("sort-icon", "header"), true);
	}

	@Then("Admin should see the delete icon in each row")
	public void admin_should_see_the_delete_icon_in_each_row() {
		Assert.assertEquals(context.getCommonPage().isElementPresent("delete-icon", "row"), true);
	}

	@Then("Admin should see the checkbox in each row")
	public void admin_should_see_the_checkbox_in_each_row() {
		Assert.assertEquals(context.getCommonPage().isElementPresent("checkbox", "row"), true);
	}

	@Then("Admin should see the checkbox  in the datatable header row")
	public void admin_should_see_the_checkbox_in_the_datatable_header_row() {
		Assert.assertEquals(context.getCommonPage().isElementPresent("checkbox", "header"), true);
	}

	@Then("Admin should see the datatable headers Batch name, Batch Description,Batch Status, No Of classes, Program Name, Edit\\/Delete")
	public void admin_should_see_the_datatable_headers_batch_name_batch_description_batch_status_no_of_classes_program_name_edit_delete() {
		List<String> expectedHeaders = Arrays.asList("Batch Name", "Batch Description", "Batch Status", "No Of Classes",
				"Program Name", "Edit / Delete");
		Assert.assertEquals(context.getCommonPage().verifyTableHeaders(expectedHeaders), true);
	}

	@Given("Admin is on the Batch Details Pop Up WIndow")
	public void admin_is_on_the_batch_details_pop_up_w_indow() {
		context.getBatchPage().batchMenuClick();
		context.getBatchPage().addBatchClick();
	}

	@When("Admin selects program name present in the dropdown")
	public void admin_selects_program_name_present_in_the_dropdown() throws Exception {
		context.getBatchPage().selectProgramNameDD();
		context.getBatchPage().selectProgramNameListBox("onlyMandatory");
	}
	
	//THISSS @TCB14
	@Then("Admin should see selected program name in the batch name prefix box")
	public void admin_should_see_selected_program_name_in_the_batch_name_prefix_box() {

		String batchName = (String) RunTimeData.getData("BatchName_All"); 
		if (batchName!=null) {
			System.out.println("BatchNameAll: " + context.getBatchPage().getProgramName());
			System.out.println("BatchNamePrefix: " + context.getBatchPage().getBatchNamePrefix());
			Assert.assertEquals(context.getBatchPage().getProgramName(), context.getBatchPage().getBatchNamePrefix()); } 
		
		else { 
			Assert.fail("BatchName_All is not present in RunTimeData");
		}
	}

	@When("Admin enters the valid data to all the mandatory fields and click cancel button")
	public void admin_enters_the_valid_data_to_all_the_mandatory_fields_and_click_cancel_button() {
		context.getBatchPage().enterAllDetails("Cancel", "onlyMandatory");
	}

	@Then("Admin can see the batch details popup closes without creating any batch")
	public void admin_can_see_the_batch_details_popup_closes_without_creating_any_batch() {
		Assert.assertEquals(context.getBatchPage().getManageBatchText(), "Manage Batch");
	}

	@Then("The pop up should include the fields Batch Name,Number of classes and Description as text box,Program Name as drop down Status as radio button")
	public void the_pop_up_should_include_the_fields_batch_name_number_of_classes_and_description_as_text_box_program_name_as_drop_down_status_as_radio_button() {
		Assert.assertEquals(context.getBatchPage().isAddBatchDescEnabled(), true);
		Assert.assertEquals(context.getBatchPage().isAddBatchNameEnabled(), true);
		Assert.assertEquals(context.getBatchPage().isAddBatchNoOfClassesEnabled(), true);
		Assert.assertEquals(context.getBatchPage().isAddBatchProgramNameEnabled(), true);
		Assert.assertEquals(context.getBatchPage().isStatusRadioButtonsPresentAndEnabled(), true);
	}

	@When("Admin enters the data only to the mandatory fields and clicks save button")
	public void admin_enters_the_data_only_to_the_mandatory_fields_and_clicks_save_button() {
		context.getBatchPage().enterAllDetails("Save", "onlyMandatory");
	}

	@Then("Admin should get a successful message")
	public void admin_should_get_a_successful_message() {
		Assert.assertEquals(context.getBatchPage().getToast(), "Successful");
		Log.logInfo("Batch created successfully");
	}

	@When("Admin clicks on {string} under the {string} menu bar")
	public void admin_clicks_on_under_the_menu_bar(String string, String string2) {
		context.getBatchPage().batchMenuClick();
		context.getBatchPage().addBatchClick();
	}

	@Then("Admin should see the Batch Details pop up window")
	public void admin_should_see_the_batch_details_pop_up_window() {
		Assert.assertEquals(context.getBatchPage().getAddNewPageTitle(), "Batch Details");
	}

	@When("Admin enters alphabets in batch name prefix box")
	public void admin_enters_alphabets_in_batch_name_prefix_box() throws Exception {
		context.getBatchPage().enterBatchNamePrefix();
	}

	@Then("Admin should see empty text box")
	public void admin_should_see_empty_text_box() {
		Assert.assertEquals(context.getBatchPage().isBatchNamePrefixEditable(), false);
	}

	@When("Admin leaves blank one of the {string}")
	public void admin_leaves_blank_one_of_the(String testcase) {
		context.getBatchPage().enterAllDetails("Save", testcase);
	}

	@Then("Admin should get a {string} on the respective mandatory field")
	public void admin_should_get_a_on_the_respective_mandatory_field(String expectedErrorMessage) {
		Assert.assertEquals(context.getBatchPage().getErrorMessage(), expectedErrorMessage);
	}

	@When("Admin clicks on the close icon")
	public void admin_clicks_on_the_close_icon() {
		context.getBatchPage().closeButtonClick();
	}

	@Then("batch details pop up closes")
	public void batch_details_pop_up_closes() {
		Assert.assertEquals(context.getBatchPage().getManageBatchText(), "Manage Batch");
	}

	@When("Admin enters {string} values in batch name suffix box")
	public void admin_enters_values_in_batch_name_suffix_box(String invalidBatchNameSuffix) {
		context.getBatchPage().enterBatchNameSuffix(invalidBatchNameSuffix);
	}

	@Then("Admin should get error message below the text box of respective field")
	public void admin_should_get_error_message_below_the_text_box_of_respective_field() {
		Assert.assertEquals(context.getBatchPage().getErrorMessage(), "This field accept only numbers and max 5 count.");
	}

	@When("Admin enters the valid data to all the fields and click save button")
	public void admin_enters_the_valid_data_to_all_the_fields_and_click_save_button() {
		context.getBatchPage().enterAllDetails("Save", "validAll");
	}

	@Given("Admin is on the Batch page")
	public void admin_is_on_the_batch_page() {
		context.getBatchPage().batchMenuClick();
	}

	@When("Admin clicks the edit icon")
	public void admin_clicks_the_edit_icon() {
		context.getBatchPage().clickAction("edit");
	}

	@Then("Admin should see Program name value field is disabled for editing")
	public void admin_should_see_program_name_value_field_is_disabled_for_editing() {
		Assert.assertEquals(context.getBatchPage().isEditBatchProgramNameEnabled(), false);
	}

	@Given("Admin is on the Batch Details Page")
	public void admin_is_on_the_batch_details_page() {
		context.getBatchPage().batchMenuClick();
		context.getBatchPage().clickAction("edit");
	}

	@When("Admin edit the valid data to all the mandatory fields and click cancel button")
	public void admin_edit_the_valid_data_to_all_the_mandatory_fields_and_click_cancel_button() {
		context.getBatchPage().editAllDetails("Cancel", "editAll");
	}

	@Then("Admin can see the batch details popup closes without editing the batch")
	public void admin_can_see_the_batch_details_popup_closes_without_editing_the_batch() {
		Assert.assertEquals(context.getBatchPage().getManageBatchText(), "Manage Batch");
	}

	@When("Admin Updates any fields with invalid data and click save button")
	public void admin_updates_any_fields_with_invalid_data_and_click_save_button() {
		context.getBatchPage().editAllDetails("Save", "editInvalid");
	}

	@Then("Admin should get a error message under the respective field")
	public void admin_should_get_a_error_message_under_the_respective_field() {
		Assert.assertEquals(context.getBatchPage().getErrorMessage(),
				"This field should start with an alphabet and min 2 character.");
	}

	@When("Admin edit the valid data to all the mandatory fields and click save button")
	public void admin_edit_the_valid_data_to_all_the_mandatory_fields_and_click_save_button() throws Exception {
		   String batchEditName = (String) RunTimeData.getData("BatchName_All");
		    if (batchEditName!=null) {
		        context.getBatchPage().enterSearch(batchEditName);
		        context.getBatchPage().clickAction("edit");
		        context.getBatchPage().editAllDetails("Save", "editAll");
		    } else {
		        Assert.fail("BatchName_All is not present in RunTimeData");
		    }
	}
	



	@Then("Admin should get a successful message for editing the batch")
	public void admin_should_get_a_successful_message_for_editing_the_batch() {
		Assert.assertEquals(context.getBatchPage().getToast(), "Successful");
		Log.logInfo("Batch updated successfully");
	}

	@Then("Admin should see batch name value field is disabled for editing")
	public void admin_should_see_batch_name_value_field_is_disabled_for_editing() {
		Assert.assertEquals(context.getBatchPage().isEditBatchNameEnabled(), false);
	}

	@Then("Admin should see the Batch details pop up window in edit")
	public void admin_should_see_the_batch_details_pop_up_window_in_edit() {
		Assert.assertEquals(context.getBatchPage().getAddNewPageTitle(), "Batch Details");
	}

	@When("Admin clicks the delete Icon on any row")
	public void admin_clicks_the_delete_icon_on_any_row() {
		context.getBatchPage().clickDelete();
	}

	@Then("Admin should see the confirm alert box with yes and no button")
	public void admin_should_see_the_confirm_alert_box_with_yes_and_no_button() {
		Assert.assertEquals(context.getBatchPage().getDeleteTitle(), "Confirm");
		Assert.assertEquals(context.getBatchPage().getDeleteNoButton(), "No");
		Assert.assertEquals(context.getBatchPage().getDeleteYesButton(), "Yes");
	}

	@Given("Admin is on the batch confirm popup page")
	public void admin_is_on_the_batch_confirm_popup_page() {
		context.getBatchPage().batchMenuClick();
		context.getBatchPage().clickDelete();
	}

	@When("Admin clicks on the delete icon and click yes button")
	public void admin_clicks_on_the_delete_icon_and_click_yes_button() throws Exception {
		context.getCommonPage().clickDeleteButtons("yes");
	}

	@Then("Admin should see the successful message and the batch should be deleted")
	public void admin_should_see_the_successful_message_and_the_batch_should_be_deleted() {
		Assert.assertEquals(context.getBatchPage().getToast(), "Successful");
		Log.logInfo("Batch deleted successfully");
	}

	@When("Admin clicks on the delete icon and click no button")
	public void admin_clicks_on_the_delete_icon_and_click_no_button() throws Exception {
		context.getCommonPage().clickDeleteButtons("no");
	}

	@Then("Admin should see the alert box closed and the batch is not deleted")
	public void admin_should_see_the_alert_box_closed_and_the_batch_is_not_deleted() {
		Assert.assertEquals(context.getBatchPage().getManageBatchText(), "Manage Batch");
	}

	@When("Admin clicks on the close icon in batch confirm popup")
	public void admin_clicks_on_the_close_icon_in_batch_confirm_popup() throws Exception {
		context.getCommonPage().clickDeleteButtons("close");
	}

	@Then("Admin should see the alert box closed")
	public void admin_should_see_the_alert_box_closed() {
		Assert.assertEquals(context.getBatchPage().getManageBatchText(), "Manage Batch");
	}

	@When("Admin clicks on the delete icon under the Manage batch header")
	public void admin_clicks_on_the_delete_icon_under_the_manage_batch_header() throws Exception {
		context.getCommonPage().storeBeforeCount();
		context.getCommonPage().selectCheckboxes(1);
		context.getCommonPage().clickdeleteAllButton();
		context.getCommonPage().clickDeleteButtons("yes");
		context.getBatchPage().batchMenuClick();
	}

	@Then("Selected Batch should be deleted")
	public void selected_batch_should_be_deleted() {

		for (int i = 0; i < deleteSuccessMessage.size(); i++) {
			Assert.assertEquals(deleteSuccessMessage.get(i), "Successful");
		}
	}

	@When("Admin clicks on the delete icon for multiple row under the Manage batch header")
	public void admin_clicks_on_the_delete_icon_for_multiple_row_under_the_manage_batch_header() throws Exception {
		context.getCommonPage().storeBeforeCount();
		context.getCommonPage().selectCheckboxes(3);
		context.getCommonPage().clickdeleteAllButton();
		context.getCommonPage().clickDeleteButtons("yes");
		context.getBatchPage().batchMenuClick();
	}

	@When("Admin enters the batch name in the search text box and edit the valid data and click save button")
	public void admin_enters_the_batch_name_in_the_search_text_box_and_edit_the_valid_data_and_click_save_button() throws Exception {
		String batchName = (String) RunTimeData.getData("BatchName_Mandatory"); 
		if (batchName !=null) { 
			context.getBatchPage().enterSearch(batchName); 
			context.getBatchPage().clickAction("edit"); 
			context.getBatchPage().editAllDetails("Save", "editAll"); 
		}
	}

	@When("Admin enters the batch name in the search text box")
	public void admin_enters_the_batch_name_in_the_search_text_box() throws Exception {
		String StoredbatchName = (String) RunTimeData.getData("BatchName_All"); 
		System.out.println("BatchNameAll in search: " + StoredbatchName);
		if (StoredbatchName!=null) { 
			context.getBatchPage().enterSearch(StoredbatchName); 
		}
	}

	@Then("Admin should see the filtered batches in the data table")
	public void admin_should_see_the_filtered_batches_in_the_data_table() {
		Assert.assertEquals(true, context.getBatchPage().validateSearch());
	}

	@Given("Admin is on the Batch page for logout")
	public void admin_is_on_the_batch_page_for_logout() {
		context.getBatchPage().batchMenuClick();
		context.getBatchPage().isElementIntercepted();
	}

	@When("Admin enters the batch name in the search text box and click on delete icon")
	public void admin_enters_the_batch_name_in_the_search_text_box_and_click_on_delete_icon() throws Exception {
		String batchName = (String) RunTimeData.getData("BatchName_Mandatory"); 
		if (batchName!=null) { 
			context.getBatchPage().enterSearch(batchName); 
		context.getBatchPage().clickAction("edit"); 
		context.getBatchPage().editAllDetails("Save", "editAll"); 
		}
	}

	@When("Admin enters the batch name in the search and click on delete icon")
	public void admin_enters_the_batch_name_in_the_search_and_click_on_delete_icon() throws Exception {

		List<String> batches = new ArrayList<String>();
		String batchNameAll = (String) RunTimeData.getData("BatchName_All"); 
		String batchNameMandatory = (String) RunTimeData.getData("BatchName_Mandatory");
		if (batchNameAll!=null) {
		    batches.add(batchNameAll);
		}
		if (batchNameMandatory!=null) {
		    batches.add(batchNameMandatory);
		}

		deleteSuccessMessage = new ArrayList<>();
		for (String batchName : batches) {
		    context.getBatchPage().enterSearch(batchName);
		    context.getBatchPage().clickAction("delete");
		    context.getCommonPage().clickDeleteButtons("yes");
		}
	}

	@Then("Selected batches should get deleted")
	public void selected_batches_should_get_deleted() {
		Assert.assertTrue(context.getCommonPage().validateCount());
	}

	@When("Admin clicks next page link on the data table")
	public void admin_clicks_next_page_link_on_the_data_table() {
		context.getBatchPage().clickOnNextPage();
	}

	@Then("Admin should see the Next enabled link")
	public void admin_should_see_the_next_enabled_link() {
		boolean nextPageActive = context.getBatchPage().nextPageEnabled();
		Assert.assertTrue(nextPageActive);

	}

	@When("Admin clicks last page link on the data table")
	public void admin_clicks_last_page_link_on_the_data_table() {
		context.getBatchPage().clickOnLastPage();
	}

	@Then("Admin should see the last page link with next page link disabled on the table")
	public void admin_should_see_the_last_page_link_with_next_page_link_disabled_on_the_table() {
		boolean nextPageDisabled = context.getBatchPage().verifyNextPageBtnDisabled();
		boolean lastPageDisplayed = context.getBatchPage().lastPageDisplayed();
		Assert.assertTrue(nextPageDisabled);
		Assert.assertTrue(lastPageDisplayed);
	}

	@When("Admin clicks previous page link on the data table")
	public void admin_clicks_previous_page_link_on_the_data_table() {
		context.getBatchPage().clickOnThirdPage();
		context.getBatchPage().clickOnPreviuosPage();
	}

	@Then("Admin should see the previous page on the table")
	public void admin_should_see_the_previous_page_on_the_table() {
		boolean previousPageActive = context.getBatchPage().previousPageEnabled();
		Assert.assertTrue(previousPageActive);
	}

	@When("Admin clicks first page link on the data table")
	public void admin_clicks_first_page_link_on_the_data_table() {
		context.getBatchPage().clickOnFirstPage();
	}

	@Then("Admin should see the very first page on the data table")
	public void admin_should_see_the_very_first_page_on_the_data_table() {
		String pageText = context.getBatchPage().firstPageValidation();
		Assert.assertTrue(pageText.contains("Showing 1"));
	}

	@When("Admin clicks on Arrow next to {string} of Batch module page for sort ascending")
	public void admin_clicks_on_arrow_next_to_of_batch_module_page_for_sort_ascending(String columnName) {
		context.getBatchPage().columnNameSorting(columnName, 1);
	}

	@When("Admin clicks on Arrow next to {string} of Batch module page for sort descending")
	public void admin_clicks_on_arrow_next_to_of_batch_module_page_for_sort_descending(String columnName) {
		context.getBatchPage().columnNameSorting(columnName, 3);
	}

	@Then("Admin should see the sorted list for {string}")
	public void admin_should_see_the_sorted_list(String columnName) {
		List<String> originalList = context.getBatchPage().getOriginalList(columnName);
		List<String> sortedList = context.getBatchPage().getSortedList(originalList);
		Assert.assertTrue(originalList.equals(sortedList));
	}

}
