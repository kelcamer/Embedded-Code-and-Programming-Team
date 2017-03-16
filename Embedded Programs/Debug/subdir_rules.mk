################################################################################
# Automatically-generated file. Do not edit!
################################################################################

# Each subdirectory must supply rules for building sources it contributes
main.obj: ../main.c $(GEN_OPTS) $(GEN_HDRS)
	@echo 'Building file: $<'
	@echo 'Invoking: ARM Compiler'
	"/Applications/ti/ccsv6/tools/compiler/ti-cgt-arm_5.2.6/bin/armcl" -mv4 --code_state=32 --abi=ti_arm9_abi --include_path="/Applications/ti/ccsv6/tools/compiler/ti-cgt-arm_5.2.6/include" --include_path="/Applications/ti/ccsv6/tools/compiler/ti-cgt-msp430_4.4.6/include" --include_path="/Applications/ti/ccsv6/tools/compiler/ti-cgt-msp430_4.4.6/bin" --include_path="/Applications/ti/ccsv6/ccs_base/msp430/include" --include_path="/Applications/ti/ccsv6/ccs_base/msp430/include_gcc" -g --display_error_number --diag_warning=225 --diag_wrap=off --preproc_with_compile --preproc_dependency="main.pp" $(GEN_OPTS__FLAG) "$<"
	@echo 'Finished building: $<'
	@echo ' '


