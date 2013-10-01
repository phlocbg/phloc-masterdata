/**
 * Copyright (C) 2006-2013 phloc systems
 * http://www.phloc.com
 * office[at]phloc[dot]com
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *         http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.phloc.masterdata.unit;

import java.util.Locale;

import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.phloc.commons.id.IHasSimpleIntID;
import com.phloc.commons.lang.EnumHelper;
import com.phloc.commons.name.IHasDisplayText;

public enum EUnitQuantity implements IHasSimpleIntID, IHasDisplayText
{
  ANGLE_PLANE (1, EUnitQuantityName.ANGLE_PLANE),
  SOLID_ANGLE (2, EUnitQuantityName.SOLID_ANGLE),
  LENGTH_BREADTH_PLUS (3, EUnitQuantityName.LENGTH_BREADTH_PLUS),
  AREA (4, EUnitQuantityName.AREA),
  VOLUME (5, EUnitQuantityName.VOLUME),
  VOLUME_PER_TEMPERATURE (6, EUnitQuantityName.VOLUME_PER_TEMPERATURE),
  VOLUME_RATIO (7, EUnitQuantityName.VOLUME_RATIO),
  TIME (8, EUnitQuantityName.TIME),
  ANGULAR_VELOCITY (9, EUnitQuantityName.ANGULAR_VELOCITY),
  ANGULAR_ACCELERATION (10, EUnitQuantityName.ANGULAR_ACCELERATION),
  VELOCITY_PHASE_VELOCITY_PLUS (11, EUnitQuantityName.VELOCITY_PHASE_VELOCITY_PLUS),
  ACCELERATION_ACCELERATION_OF_FREE_FALL_PLUS (12, EUnitQuantityName.ACCELERATION_ACCELERATION_OF_FREE_FALL_PLUS),
  CURVATURE (13, EUnitQuantityName.CURVATURE),
  FREQUENCY (14, EUnitQuantityName.FREQUENCY),
  ROTATIONAL_FREQUENCY (15, EUnitQuantityName.ROTATIONAL_FREQUENCY),
  ANGULAR_FREQUENCY_PULSATANCE (16, EUnitQuantityName.ANGULAR_FREQUENCY_PULSATANCE),
  WAVELENGTH (17, EUnitQuantityName.WAVELENGTH),
  WAVE_NUMBER_ATTENUATION_COEFFICIENT_PLUS (18, EUnitQuantityName.WAVE_NUMBER_ATTENUATION_COEFFICIENT_PLUS),
  @Deprecated
  LEVEL_OF_A_FIELD_QUANTITY_LEVEL_OF_A_POWER_QUANTITY (19, EUnitQuantityName.LEVEL_OF_A_FIELD_QUANTITY_LEVEL_OF_A_POWER_QUANTITY),
  DAMPING_COEFFICIENT (20, EUnitQuantityName.DAMPING_COEFFICIENT),
  LOGARITHMIC_DECREMENT (21, EUnitQuantityName.LOGARITHMIC_DECREMENT),
  MASS (22, EUnitQuantityName.MASS),
  DENSITY_MASS_DENSITY_PLUS (23, EUnitQuantityName.DENSITY_MASS_DENSITY_PLUS),
  RELATIVE_DENSITY_RELATIVE_MASS_DENSITY (24, EUnitQuantityName.RELATIVE_DENSITY_RELATIVE_MASS_DENSITY),
  SPECIFIC_VOLUME_MASSIC_VOLUME (25, EUnitQuantityName.SPECIFIC_VOLUME_MASSIC_VOLUME),
  LINEAR_DENSITY_LINEAR_MASS (26, EUnitQuantityName.LINEAR_DENSITY_LINEAR_MASS),
  SURFACE_DENSITY_AREIC_MASS (27, EUnitQuantityName.SURFACE_DENSITY_AREIC_MASS),
  MOMENTUM (28, EUnitQuantityName.MOMENTUM),
  MOMENT_OF_MOMENTUM_ANGULAR_MOMENTUM (29, EUnitQuantityName.MOMENT_OF_MOMENTUM_ANGULAR_MOMENTUM),
  MOMENT_OF_INERTIA_DYNAMIC_MOMENT_OF_INERTIA (30, EUnitQuantityName.MOMENT_OF_INERTIA_DYNAMIC_MOMENT_OF_INERTIA),
  FORCE_WEIGHT (31, EUnitQuantityName.FORCE_WEIGHT),
  FORCE_DIVIDED_BY_LENGTH (32, EUnitQuantityName.FORCE_DIVIDED_BY_LENGTH),
  GRAVITATIONAL_CONSTANT (33, EUnitQuantityName.GRAVITATIONAL_CONSTANT),
  MOMENT_OF_FORCE_MOMENT_OF_A_COUPLE_PLUS (34, EUnitQuantityName.MOMENT_OF_FORCE_MOMENT_OF_A_COUPLE_PLUS),
  IMPULSE (35, EUnitQuantityName.IMPULSE),
  ANGULAR_IMPULSE (36, EUnitQuantityName.ANGULAR_IMPULSE),
  PRESSURE_NORMAL_STRESS_PLUS (37, EUnitQuantityName.PRESSURE_NORMAL_STRESS_PLUS),
  PRESSURE_RATIO (38, EUnitQuantityName.PRESSURE_RATIO),
  LINEAR_STRAIN_RELATIVE_ELONGATION_PLUS (39, EUnitQuantityName.LINEAR_STRAIN_RELATIVE_ELONGATION_PLUS),
  POISSON_RATIO_POISSON_NUMBER (40, EUnitQuantityName.POISSON_RATIO_POISSON_NUMBER),
  COMPRESSIBILITY_BULK_COMPRESSIBILITY (41, EUnitQuantityName.COMPRESSIBILITY_BULK_COMPRESSIBILITY),
  SECOND_MOMENT_OF_AREA_SECOND_AXIAL_MOMENT_OF_AREA (42, EUnitQuantityName.SECOND_MOMENT_OF_AREA_SECOND_AXIAL_MOMENT_OF_AREA),
  SECOND_POLAR_MOMENT_OF_AREA (43, EUnitQuantityName.SECOND_POLAR_MOMENT_OF_AREA),
  SECTION_MODULUS (44, EUnitQuantityName.SECTION_MODULUS),
  FRICTION_FACTOR_COEFFICIENT_OF_FRICTION (45, EUnitQuantityName.FRICTION_FACTOR_COEFFICIENT_OF_FRICTION),
  VISCOSITY_DYNAMIC_VISCOSITY (46, EUnitQuantityName.VISCOSITY_DYNAMIC_VISCOSITY),
  KINEMATIC_VISCOSITY (47, EUnitQuantityName.KINEMATIC_VISCOSITY),
  SURFACE_TENSION (48, EUnitQuantityName.SURFACE_TENSION),
  TORSIONAL_STIFFNESS_AREA_RELATED_TORSIONAL_MOMENT (49, EUnitQuantityName.TORSIONAL_STIFFNESS_AREA_RELATED_TORSIONAL_MOMENT),
  WORK_ENERGY_PLUS (50, EUnitQuantityName.WORK_ENERGY_PLUS),
  WORK_PER_UNIT_WEIGHT (51, EUnitQuantityName.WORK_PER_UNIT_WEIGHT),
  POWER (52, EUnitQuantityName.POWER),
  MASS_FLOW_RATE (53, EUnitQuantityName.MASS_FLOW_RATE),
  MASS_RATIO (54, EUnitQuantityName.MASS_RATIO),
  VOLUME_FLOW_RATE (55, EUnitQuantityName.VOLUME_FLOW_RATE),
  LEAKAGE_RATE_OF_GAS (56, EUnitQuantityName.LEAKAGE_RATE_OF_GAS),
  THERMODYNAMIC (57, EUnitQuantityName.THERMODYNAMIC),
  TEMPERATURE (58, EUnitQuantityName.TEMPERATURE),
  FAHRENHEIT_TEMPERATURE (59, EUnitQuantityName.FAHRENHEIT_TEMPERATURE),
  TEMPERATURE_VARIATION_OVER_TIME (60, EUnitQuantityName.TEMPERATURE_VARIATION_OVER_TIME),
  LINEAR_EXPANSION_COEFFICIENT_CUBIC_EXPANSION_COEFFICIENT_PLUS (61, EUnitQuantityName.LINEAR_EXPANSION_COEFFICIENT_CUBIC_EXPANSION_COEFFICIENT_PLUS),
  PRESSURE_COEFFICIENT (62, EUnitQuantityName.PRESSURE_COEFFICIENT),
  ISOTHERMAL_COMPRESSIBILITY_ISENTROPIC_COMPRESSIBILITY (63, EUnitQuantityName.ISOTHERMAL_COMPRESSIBILITY_ISENTROPIC_COMPRESSIBILITY),
  HEAT_QUANTITY_OF_HEAT_PLUS (64, EUnitQuantityName.HEAT_QUANTITY_OF_HEAT_PLUS),
  GIBBS_FUNCTION_GIBBS_FREE_ENERGY (65, EUnitQuantityName.GIBBS_FUNCTION_GIBBS_FREE_ENERGY),
  HEAT_FLOW_RATE (66, EUnitQuantityName.HEAT_FLOW_RATE),
  DENSITY_OF_HEAT_FLOW_RATE (67, EUnitQuantityName.DENSITY_OF_HEAT_FLOW_RATE),
  THERMAL_CONDUCTIVITY (68, EUnitQuantityName.THERMAL_CONDUCTIVITY),
  COEFFICIENT_OF_HEAT_TRANSFER (69, EUnitQuantityName.COEFFICIENT_OF_HEAT_TRANSFER),
  SURFACE_COEFFICIENT_OF_HEAT_TRANSFER (70, EUnitQuantityName.SURFACE_COEFFICIENT_OF_HEAT_TRANSFER),
  THERMAL_INSULANCE_COEFFICIENT_OF_THERMAL_INSULATION (71, EUnitQuantityName.THERMAL_INSULANCE_COEFFICIENT_OF_THERMAL_INSULATION),
  THERMAL_RESISTANCE (72, EUnitQuantityName.THERMAL_RESISTANCE),
  THERMAL_CONDUCTANCE (73, EUnitQuantityName.THERMAL_CONDUCTANCE),
  THERMAL_DIFFUSIVITY (74, EUnitQuantityName.THERMAL_DIFFUSIVITY),
  HEAT_CAPACITY_ENTROPY (75, EUnitQuantityName.HEAT_CAPACITY_ENTROPY),
  SPECIFIC_HEAT_CAPACITY_AT_CONSTANT_PRESSURE_CONSTANT_VOLUME_PLUS (76, EUnitQuantityName.SPECIFIC_HEAT_CAPACITY_AT_CONSTANT_PRESSURE_CONSTANT_VOLUME_PLUS),
  RATIO_OF_THE_SPECIFIC_HEAT_CAPACITIES_RATIO_OF_THE_MASSIC_HEAT_CAPACITY_PLUS (77, EUnitQuantityName.RATIO_OF_THE_SPECIFIC_HEAT_CAPACITIES_RATIO_OF_THE_MASSIC_HEAT_CAPACITY_PLUS),
  MASSIEU_FUNCTION_PLANCK_FUNCTION (78, EUnitQuantityName.MASSIEU_FUNCTION_PLANCK_FUNCTION),
  MASSIC_ENERGY_SPECIFIC_ENERGY (79, EUnitQuantityName.MASSIC_ENERGY_SPECIFIC_ENERGY),
  MASSIC_THERMODYNAMIC_ENERGY (80, EUnitQuantityName.MASSIC_THERMODYNAMIC_ENERGY),
  SPECIFIC_THERMODYNAMIC_ENERGY (81, EUnitQuantityName.SPECIFIC_THERMODYNAMIC_ENERGY),
  MASSIC_ENTHALPY_SPECIFIC_ENTHALPY (82, EUnitQuantityName.MASSIC_ENTHALPY_SPECIFIC_ENTHALPY),
  MASSIC_HELMHOLTZ_FREE_ENERGY (83, EUnitQuantityName.MASSIC_HELMHOLTZ_FREE_ENERGY),
  SPECIFIC_HELMHOLTZ_FREE_ENERGY (84, EUnitQuantityName.SPECIFIC_HELMHOLTZ_FREE_ENERGY),
  SPECIFIC_HELMHOLTZ_FUNCTION_MASSIC_GIBBS_FREE_ENERGY_PLUS (85, EUnitQuantityName.SPECIFIC_HELMHOLTZ_FUNCTION_MASSIC_GIBBS_FREE_ENERGY_PLUS),
  ENERGY_DENSITY (86, EUnitQuantityName.ENERGY_DENSITY),
  ELECTRIC_CURRENT_MAGNETIC_POTENTIAL_DIFFERENCE_PLUS (87, EUnitQuantityName.ELECTRIC_CURRENT_MAGNETIC_POTENTIAL_DIFFERENCE_PLUS),
  ELECTRIC_CHARGE_QUANTITY_OF_ELECTRICITY_PLUS (88, EUnitQuantityName.ELECTRIC_CHARGE_QUANTITY_OF_ELECTRICITY_PLUS),
  VOLUME_DENSITY_OF_CHARGE_CHARGE_DENSITY_PLUS (89, EUnitQuantityName.VOLUME_DENSITY_OF_CHARGE_CHARGE_DENSITY_PLUS),
  SURFACE_DENSITY_OF_CHARGE_ELECTRIC_FLUX_DENSITY_PLUS (90, EUnitQuantityName.SURFACE_DENSITY_OF_CHARGE_ELECTRIC_FLUX_DENSITY_PLUS),
  ELECTRIC_FIELD_STRENGTH (91, EUnitQuantityName.ELECTRIC_FIELD_STRENGTH),
  ELECTRIC_POTENTIAL_POTENTIAL_DIFFERENCE_PLUS (92, EUnitQuantityName.ELECTRIC_POTENTIAL_POTENTIAL_DIFFERENCE_PLUS),
  CAPACITANCE (93, EUnitQuantityName.CAPACITANCE),
  PERMITTIVITY_PERMITTIVITY_OF_VACUUM_PLUS (94, EUnitQuantityName.PERMITTIVITY_PERMITTIVITY_OF_VACUUM_PLUS),
  RELATIVE_PERMITTIVITY (95, EUnitQuantityName.RELATIVE_PERMITTIVITY),
  ELECTRIC_SUSCEPTIBILITY (96, EUnitQuantityName.ELECTRIC_SUSCEPTIBILITY),
  ELECTRIC_DIPOLE_MOMENT (97, EUnitQuantityName.ELECTRIC_DIPOLE_MOMENT),
  CURRENT_DENSITY (98, EUnitQuantityName.CURRENT_DENSITY),
  LINEAR_ELECTRIC_CURRENT_DENSITY_LINEIC_ELECTRIC_CURRENT_PLUS (99, EUnitQuantityName.LINEAR_ELECTRIC_CURRENT_DENSITY_LINEIC_ELECTRIC_CURRENT_PLUS),
  LINEIC_CHARGE (100, EUnitQuantityName.LINEIC_CHARGE),
  MAGNETIC_FLUX_DENSITY_MAGNETIC_INDUCTION_PLUS (101, EUnitQuantityName.MAGNETIC_FLUX_DENSITY_MAGNETIC_INDUCTION_PLUS),
  MAGNETIC_FLUX (102, EUnitQuantityName.MAGNETIC_FLUX),
  MAGNETIC_VECTOR_POTENTIAL (103, EUnitQuantityName.MAGNETIC_VECTOR_POTENTIAL),
  SELF_INDUCTANCE_MUTUAL_INDUCTANCE_PLUS (104, EUnitQuantityName.SELF_INDUCTANCE_MUTUAL_INDUCTANCE_PLUS),
  COUPLING_COEFFICIENT_LEAKAGE_COEFFICIENT (105, EUnitQuantityName.COUPLING_COEFFICIENT_LEAKAGE_COEFFICIENT),
  NUMBER_OF_TURNS_IN_A_WINDING_NUMBER_OF_PHASES_PLUS (106, EUnitQuantityName.NUMBER_OF_TURNS_IN_A_WINDING_NUMBER_OF_PHASES_PLUS),
  PERMEABILITY_PERMEABILITY_OF_VACUUM_PLUS (107, EUnitQuantityName.PERMEABILITY_PERMEABILITY_OF_VACUUM_PLUS),
  RELATIVE_PERMEABILITY (108, EUnitQuantityName.RELATIVE_PERMEABILITY),
  MAGNETIC_SUSCEPTIBILITY (109, EUnitQuantityName.MAGNETIC_SUSCEPTIBILITY),
  ELECTROMAGNETIC_MOMENT_MAGNETIC_MOMENT_PLUS (110, EUnitQuantityName.ELECTROMAGNETIC_MOMENT_MAGNETIC_MOMENT_PLUS),
  MAGNETIZATION (111, EUnitQuantityName.MAGNETIZATION),
  ELECTROMAGNETIC_ENERGY_DENSITY_VOLUMIC_ELECTROMAGNETIC_ENERGY (112, EUnitQuantityName.ELECTROMAGNETIC_ENERGY_DENSITY_VOLUMIC_ELECTROMAGNETIC_ENERGY),
  POYNTING_VECTOR (113, EUnitQuantityName.POYNTING_VECTOR),
  PHASE_VELOCITY_OF_ELECTROMAGNETIC_WAVES_PHASE_SPEED_OF_ELECTROMAGNETIC_WAVES (114, EUnitQuantityName.PHASE_VELOCITY_OF_ELECTROMAGNETIC_WAVES_PHASE_SPEED_OF_ELECTROMAGNETIC_WAVES),
  RESISTANCE_TO_DIRECT_CURRENT_IMPEDANCE_PLUS (115, EUnitQuantityName.RESISTANCE_TO_DIRECT_CURRENT_IMPEDANCE_PLUS),
  RESISTANCE_LOAD_PER_UNIT_LENGTH (116, EUnitQuantityName.RESISTANCE_LOAD_PER_UNIT_LENGTH),
  CONDUCTANCE_FOR_DIRECT_CURRENT_ADMITTANCE_PLUS (117, EUnitQuantityName.CONDUCTANCE_FOR_DIRECT_CURRENT_ADMITTANCE_PLUS),
  SUSCEPTANCE (118, EUnitQuantityName.SUSCEPTANCE),
  RESISTIVITY (119, EUnitQuantityName.RESISTIVITY),
  LINEIC_RESISTANCE (120, EUnitQuantityName.LINEIC_RESISTANCE),
  CONDUCTIVITY (121, EUnitQuantityName.CONDUCTIVITY),
  RELUCTANCE (122, EUnitQuantityName.RELUCTANCE),
  PHASE_DIFFERENCE_PHASE_DISPLACEMENT_PLUS (123, EUnitQuantityName.PHASE_DIFFERENCE_PHASE_DISPLACEMENT_PLUS),
  POWER_FOR_DIRECT_CURRENT_ACTIVE_POWER (124, EUnitQuantityName.POWER_FOR_DIRECT_CURRENT_ACTIVE_POWER),
  APPARENT_POWER (125, EUnitQuantityName.APPARENT_POWER),
  REACTIVE_POWER (126, EUnitQuantityName.REACTIVE_POWER),
  ACTIVE_ENERGY (127, EUnitQuantityName.ACTIVE_ENERGY),
  COEFFICIENT_PERFORMANCE_CHARACTERISTIC (128, EUnitQuantityName.COEFFICIENT_PERFORMANCE_CHARACTERISTIC),
  CIRCULAR_FREQUENCY (129, EUnitQuantityName.CIRCULAR_FREQUENCY),
  WAVENUMBER_REPETENCY (130, EUnitQuantityName.WAVENUMBER_REPETENCY),
  ANGULAR_WAVE_NUMBER_ANGULAR_REPETENCY (131, EUnitQuantityName.ANGULAR_WAVE_NUMBER_ANGULAR_REPETENCY),
  VELOCITY_SPEED_ON_PROPAGATION_OF_ELECTROMAGNETIC_WAVES_IN_VACUO (132, EUnitQuantityName.VELOCITY_SPEED_ON_PROPAGATION_OF_ELECTROMAGNETIC_WAVES_IN_VACUO),
  RADIANT_ENERGY (133, EUnitQuantityName.RADIANT_ENERGY),
  RADIANT_ENERGY_DENSITY (134, EUnitQuantityName.RADIANT_ENERGY_DENSITY),
  SPECTRAL_CONCENTRATION_OF_RADIANT_ENERGY_DENSITY_IN_TERMS_OF_WAVELENGTH_SPECTRAL_RADIANT_ENERGY_DENSITY_PLUS (135, EUnitQuantityName.SPECTRAL_CONCENTRATION_OF_RADIANT_ENERGY_DENSITY_IN_TERMS_OF_WAVELENGTH_SPECTRAL_RADIANT_ENERGY_DENSITY_PLUS),
  RADIANT_POWER_RADIANT_ENERGYFLUX (136, EUnitQuantityName.RADIANT_POWER_RADIANT_ENERGYFLUX),
  RADIANT_ENERGY_FLUENCE_RADIANCE_EXPOSURE (137, EUnitQuantityName.RADIANT_ENERGY_FLUENCE_RADIANCE_EXPOSURE),
  PHOTON_FLUX (138, EUnitQuantityName.PHOTON_FLUX),
  PHOTON_INTENSITY (139, EUnitQuantityName.PHOTON_INTENSITY),
  PHOTON_LUMINANCE_PHOTON_RADIANCE (140, EUnitQuantityName.PHOTON_LUMINANCE_PHOTON_RADIANCE),
  PHOTON_EXITANCE_IRRADIANCE (141, EUnitQuantityName.PHOTON_EXITANCE_IRRADIANCE),
  PHOTON_EXPOSURE (142, EUnitQuantityName.PHOTON_EXPOSURE),
  RADIANT_ENERGY_FLUENCE_RATE_PLUS (143, EUnitQuantityName.RADIANT_ENERGY_FLUENCE_RATE_PLUS),
  RADIANT_INTENSITY (144, EUnitQuantityName.RADIANT_INTENSITY),
  RADIANCE (145, EUnitQuantityName.RADIANCE),
  STEFAN_BOLTZMANN_CONSTANT (146, EUnitQuantityName.STEFAN_BOLTZMANN_CONSTANT),
  SECOND_RADIATION_CONSTANT (147, EUnitQuantityName.SECOND_RADIATION_CONSTANT),
  EMISSIVITY_SPECTRAL_EMISSIVITY_PLUS (148, EUnitQuantityName.EMISSIVITY_SPECTRAL_EMISSIVITY_PLUS),
  LUMINOUS_INTENSITY (149, EUnitQuantityName.LUMINOUS_INTENSITY),
  LUMINOUS_FLUX (150, EUnitQuantityName.LUMINOUS_FLUX),
  QUANTITY_OF_LIGHT (151, EUnitQuantityName.QUANTITY_OF_LIGHT),
  LUMINANCE (152, EUnitQuantityName.LUMINANCE),
  LUMINOUS_EXITANCE (153, EUnitQuantityName.LUMINOUS_EXITANCE),
  ILLUMINANCE (154, EUnitQuantityName.ILLUMINANCE),
  LIGHT_EXPOSURE (155, EUnitQuantityName.LIGHT_EXPOSURE),
  LUMINIOUS_EFFICACY_SPECTRAL_LUMINOUS_EFFICACY_PLUS (156, EUnitQuantityName.LUMINIOUS_EFFICACY_SPECTRAL_LUMINOUS_EFFICACY_PLUS),
  LUMINOUS_EFFICIENCY_SPECTRAL_LUMINOUS_EFFICIENCY_PLUS (157, EUnitQuantityName.LUMINOUS_EFFICIENCY_SPECTRAL_LUMINOUS_EFFICIENCY_PLUS),
  CIE_COLORIMETRIC_FUNCTIONS (158, EUnitQuantityName.CIE_COLORIMETRIC_FUNCTIONS),
  COORDINATES_TRICHROMATIC (159, EUnitQuantityName.COORDINATES_TRICHROMATIC),
  SPECTRAL_ABSORPTION_FACTOR_SPECTRAL_ABSORPTANCE_PLUS (160, EUnitQuantityName.SPECTRAL_ABSORPTION_FACTOR_SPECTRAL_ABSORPTANCE_PLUS),
  OPTICAL_DENSITY (161, EUnitQuantityName.OPTICAL_DENSITY),
  REFRACTIVE_INDEX (162, EUnitQuantityName.REFRACTIVE_INDEX),
  LINEAR_ATTENUATION_COEFFICIENT_LINEAR_EXTINCTION_COEFFICIENT_PLUS (163, EUnitQuantityName.LINEAR_ATTENUATION_COEFFICIENT_LINEAR_EXTINCTION_COEFFICIENT_PLUS),
  MOLAR_ABSORPTION_COEFFICIENT (164, EUnitQuantityName.MOLAR_ABSORPTION_COEFFICIENT),
  OBJECT_DISTANCE_IMAGE_DISTANCE_PLUS (165, EUnitQuantityName.OBJECT_DISTANCE_IMAGE_DISTANCE_PLUS),
  VERGENCE_LENS_POWER (166, EUnitQuantityName.VERGENCE_LENS_POWER),
  PERIOD_PERIODIC_TIME (167, EUnitQuantityName.PERIOD_PERIODIC_TIME),
  FREQUENCY_INTERVAL (168, EUnitQuantityName.FREQUENCY_INTERVAL),
  REPETENCY_WAVENUMBER (169, EUnitQuantityName.REPETENCY_WAVENUMBER),
  ANGULAR_REPETENCY_ANGULAR_WAVENUMBER (170, EUnitQuantityName.ANGULAR_REPETENCY_ANGULAR_WAVENUMBER),
  VOLUMIC_MASS_DENSITY_PLUS (171, EUnitQuantityName.VOLUMIC_MASS_DENSITY_PLUS),
  STATIC_PRESSURE_INSTANTANEOUS_SOUND_PRESSURE (172, EUnitQuantityName.STATIC_PRESSURE_INSTANTANEOUS_SOUND_PRESSURE),
  INSTANTANEOUS_SOUND_PARTICLE_DISPLACEMENT (173, EUnitQuantityName.INSTANTANEOUS_SOUND_PARTICLE_DISPLACEMENT),
  INSTANTANEOUS_SOUND_PARTICLE_VELOCITY (174, EUnitQuantityName.INSTANTANEOUS_SOUND_PARTICLE_VELOCITY),
  INSTANTANEOUS_SOUND_PARTICLE_ACCELERATION (175, EUnitQuantityName.INSTANTANEOUS_SOUND_PARTICLE_ACCELERATION),
  INSTANTANEOUS_VOLUME_FLOW_RATE (176, EUnitQuantityName.INSTANTANEOUS_VOLUME_FLOW_RATE),
  VELOCITY_OF_SOUND_PHASE_VELOCITY_GROUP_VELOCITY (177, EUnitQuantityName.VELOCITY_OF_SOUND_PHASE_VELOCITY_GROUP_VELOCITY),
  SOUND_ENERGY_DENSITY_VOLUMIC_PLUS (178, EUnitQuantityName.SOUND_ENERGY_DENSITY_VOLUMIC_PLUS),
  SOUND_POWER (179, EUnitQuantityName.SOUND_POWER),
  SOUND_INTENSITY (180, EUnitQuantityName.SOUND_INTENSITY),
  CHARACTERISTIC_IMPEDANCE_OF_A_MEDIUM (181, EUnitQuantityName.CHARACTERISTIC_IMPEDANCE_OF_A_MEDIUM),
  SURFACE_DENSITY_OF_MECHANICAL_IMPEDANCE (182, EUnitQuantityName.SURFACE_DENSITY_OF_MECHANICAL_IMPEDANCE),
  ACOUSTIC_IMPEDANCE (183, EUnitQuantityName.ACOUSTIC_IMPEDANCE),
  MECHANICAL_IMPEDANCE (184, EUnitQuantityName.MECHANICAL_IMPEDANCE),
  SOUND_PRESSURE_LEVEL_SOUND_POWER_LEVEL (185, EUnitQuantityName.SOUND_PRESSURE_LEVEL_SOUND_POWER_LEVEL),
  TIME_CONSTANT_RELAXATION_TIME (186, EUnitQuantityName.TIME_CONSTANT_RELAXATION_TIME),
  ATTENUATION_COEFFICIENT_PHASE_COEFFICIENT_PLUS (187, EUnitQuantityName.ATTENUATION_COEFFICIENT_PHASE_COEFFICIENT_PLUS),
  DISSIPATION_FACTOR_DISSIPANCE_PLUS (188, EUnitQuantityName.DISSIPATION_FACTOR_DISSIPANCE_PLUS),
  SOUND_REDUCTION_INDEX (189, EUnitQuantityName.SOUND_REDUCTION_INDEX),
  EQUIVALENT_ABSORPTION_AREA_OF_A_SURFACE_OR_OBJECT (190, EUnitQuantityName.EQUIVALENT_ABSORPTION_AREA_OF_A_SURFACE_OR_OBJECT),
  REVERBERATION_TIME (191, EUnitQuantityName.REVERBERATION_TIME),
  LOUDNESS_LEVEL (192, EUnitQuantityName.LOUDNESS_LEVEL),
  LOUDNESS (193, EUnitQuantityName.LOUDNESS),
  SOUND_EXPOSURE (194, EUnitQuantityName.SOUND_EXPOSURE),
  RELATIVE_ATOMIC_MASS (195, EUnitQuantityName.RELATIVE_ATOMIC_MASS),
  RELATIVE_MOLECULAR_MASS (196, EUnitQuantityName.RELATIVE_MOLECULAR_MASS),
  NUMBER_OF_MOLECULES_OR_OTHER_ELEMENTARY_ENTITIES (197, EUnitQuantityName.NUMBER_OF_MOLECULES_OR_OTHER_ELEMENTARY_ENTITIES),
  AMOUNT_OF_SUBSTANCE (198, EUnitQuantityName.AMOUNT_OF_SUBSTANCE),
  AVOGADRO_CONSTANT (199, EUnitQuantityName.AVOGADRO_CONSTANT),
  MOLAR_MASS (200, EUnitQuantityName.MOLAR_MASS),
  MOLAR_VOLUME (201, EUnitQuantityName.MOLAR_VOLUME),
  MOLAR_THERMODYNAMIC_ENERGY (202, EUnitQuantityName.MOLAR_THERMODYNAMIC_ENERGY),
  CHEMICAL_POTENTIAL (203, EUnitQuantityName.CHEMICAL_POTENTIAL),
  ABSOLUTE_ACTIVITY (204, EUnitQuantityName.ABSOLUTE_ACTIVITY),
  AFFINITY_OF_A_CHEMICAL_REACTION (205, EUnitQuantityName.AFFINITY_OF_A_CHEMICAL_REACTION),
  STANDARD_EQUILIBRIUM_CONSTANT (206, EUnitQuantityName.STANDARD_EQUILIBRIUM_CONSTANT),
  MOLAR_HEAT_CAPACITY_MOLAR_ENTROPY_PLUS (207, EUnitQuantityName.MOLAR_HEAT_CAPACITY_MOLAR_ENTROPY_PLUS),
  VOLUMIC_NUMBER_OF_MOLECULES_OR_PARTICLES_NUMBER_DENSITY_OF_MOLECULES_PLUS (208, EUnitQuantityName.VOLUMIC_NUMBER_OF_MOLECULES_OR_PARTICLES_NUMBER_DENSITY_OF_MOLECULES_PLUS),
  VOLUMIC_MASS_MASS_DENSITY_PLUS (209, EUnitQuantityName.VOLUMIC_MASS_MASS_DENSITY_PLUS),
  MOLE_FRACTION_OF_B_MOLE_RATIO_OF_SOLUTE_B (210, EUnitQuantityName.MOLE_FRACTION_OF_B_MOLE_RATIO_OF_SOLUTE_B),
  MOLALITY_OF_SOLUTE_B (211, EUnitQuantityName.MOLALITY_OF_SOLUTE_B),
  VOLUMIC_DOSE (212, EUnitQuantityName.VOLUMIC_DOSE),
  IONIC_STRENGTH (213, EUnitQuantityName.IONIC_STRENGTH),
  DEGREE_OF_DISSOCIATION (214, EUnitQuantityName.DEGREE_OF_DISSOCIATION),
  PARTIAL_PRESSURE_OF_B_IN_A_GASEOUS_MIXTURE_FUGACITY_OF_B_PLUS (215, EUnitQuantityName.PARTIAL_PRESSURE_OF_B_IN_A_GASEOUS_MIXTURE_FUGACITY_OF_B_PLUS),
  STANDARD_ABSOLUTE_ACTIVITY_OF_B_IN_A_GASEOUS_MIXTURE (216, EUnitQuantityName.STANDARD_ABSOLUTE_ACTIVITY_OF_B_IN_A_GASEOUS_MIXTURE),
  ACTIVITY_COEFFICIENT_OF_B_IN_A_LIQUID_AS_A_SOLID_MIXTURE (217, EUnitQuantityName.ACTIVITY_COEFFICIENT_OF_B_IN_A_LIQUID_AS_A_SOLID_MIXTURE),
  STANDARD_ABSOLUTE_ACTIVITY_OF_B_IN_A_LIQUID_OR_A_SOLID_MIXTURE (218, EUnitQuantityName.STANDARD_ABSOLUTE_ACTIVITY_OF_B_IN_A_LIQUID_OR_A_SOLID_MIXTURE),
  ACTIVITY_COEFFICIENT_OF_SOLUTE_B_ESPECIALLY_IN_A_DILUTE_SOLUTION_STANDARD_ABSOLUTE_ACTIVITY_OF_SOLUTE_B_PLUS (219, EUnitQuantityName.ACTIVITY_COEFFICIENT_OF_SOLUTE_B_ESPECIALLY_IN_A_DILUTE_SOLUTION_STANDARD_ABSOLUTE_ACTIVITY_OF_SOLUTE_B_PLUS),
  ACTIVITY_OF_SOLVENT_A_RELATIVE_ACTIVITY_OF_SOLVENT_A_ESPECIALLY_IN_A_DILUTE_SOLUTION_PLUS (220, EUnitQuantityName.ACTIVITY_OF_SOLVENT_A_RELATIVE_ACTIVITY_OF_SOLVENT_A_ESPECIALLY_IN_A_DILUTE_SOLUTION_PLUS),
  CATALYTIC_ACTIVITY (221, EUnitQuantityName.CATALYTIC_ACTIVITY),
  STOICHIOMETRIC_NUMBER_OF_B (222, EUnitQuantityName.STOICHIOMETRIC_NUMBER_OF_B),
  MASS_OF_MOLECULE (223, EUnitQuantityName.MASS_OF_MOLECULE),
  ELECTRIC_DIPOLE_MOMENT_OF_MOLECULE (224, EUnitQuantityName.ELECTRIC_DIPOLE_MOMENT_OF_MOLECULE),
  ELECTRIC_POLARIZABILITY_OF_A_MOLECULE (225, EUnitQuantityName.ELECTRIC_POLARIZABILITY_OF_A_MOLECULE),
  MICROCANONICAL_PARTITION_FUNCTION_CANONICAL_PARTITION_FUNCTION_PLUS (226, EUnitQuantityName.MICROCANONICAL_PARTITION_FUNCTION_CANONICAL_PARTITION_FUNCTION_PLUS),
  STATISTICAL_WEIGHT (227, EUnitQuantityName.STATISTICAL_WEIGHT),
  BOLTZMANN_CONSTANT (228, EUnitQuantityName.BOLTZMANN_CONSTANT),
  MEAN_FREE_PATH (229, EUnitQuantityName.MEAN_FREE_PATH),
  DIFFUSION_COEFFICIENT (230, EUnitQuantityName.DIFFUSION_COEFFICIENT),
  THERMAL_DIFFUSION_RATIO_THERMAL_DIFFUSION_FACTOR (231, EUnitQuantityName.THERMAL_DIFFUSION_RATIO_THERMAL_DIFFUSION_FACTOR),
  THERMAL_DIFFUSION_COEFFICIENT (232, EUnitQuantityName.THERMAL_DIFFUSION_COEFFICIENT),
  PROTON_NUMBER (233, EUnitQuantityName.PROTON_NUMBER),
  ELEMENTARY_CHARGE (234, EUnitQuantityName.ELEMENTARY_CHARGE),
  CHARGE_NUMBER_OF_ION (235, EUnitQuantityName.CHARGE_NUMBER_OF_ION),
  FARADAY_CONSTANT (236, EUnitQuantityName.FARADAY_CONSTANT),
  ELECTROLYTIC_CONDUCTIVITY (237, EUnitQuantityName.ELECTROLYTIC_CONDUCTIVITY),
  MOLAR_CONDUCTIVITY (238, EUnitQuantityName.MOLAR_CONDUCTIVITY),
  MOLAR_FLUX (239, EUnitQuantityName.MOLAR_FLUX),
  TRANSPORT_NUMBER_OF_ION_B_CURRENT_FRACTION_OF_ION_B (240, EUnitQuantityName.TRANSPORT_NUMBER_OF_ION_B_CURRENT_FRACTION_OF_ION_B),
  ANGLE_OF_OPTICAL_ROTATION (241, EUnitQuantityName.ANGLE_OF_OPTICAL_ROTATION),
  MOLAR_OPTICAL_ROTATORY_POWER (242, EUnitQuantityName.MOLAR_OPTICAL_ROTATORY_POWER),
  MASSIC_OPTICAL_ROTATORY_POWER_PLUS (243, EUnitQuantityName.MASSIC_OPTICAL_ROTATORY_POWER_PLUS),
  MAGNETIC_DIPOLE_MOMENT (244, EUnitQuantityName.MAGNETIC_DIPOLE_MOMENT),
  ACIDITY_AND_ALKALINITY (245, EUnitQuantityName.ACIDITY_AND_ALKALINITY),
  PROTON_NUMBER_ATOMIC_NUMBER (246, EUnitQuantityName.PROTON_NUMBER_ATOMIC_NUMBER),
  NEUTRON_NUMBER (247, EUnitQuantityName.NEUTRON_NUMBER),
  NUCLEON_NUMBER_MASS_NUMBER (248, EUnitQuantityName.NUCLEON_NUMBER_MASS_NUMBER),
  MASS_OF_ATOM_OF_A_NUCLIDE_X_NUCLIDIC_MASS (249, EUnitQuantityName.MASS_OF_ATOM_OF_A_NUCLIDE_X_NUCLIDIC_MASS),
  MASS_OF_ATOM_OF_A_NUCLIDE_X_NUCLIDIC_MASS_PLUS (250, EUnitQuantityName.MASS_OF_ATOM_OF_A_NUCLIDE_X_NUCLIDIC_MASS_PLUS),
  REST_MASS_OF_ELECTRON_REST_MASS_OF_PROTON_PLUS (251, EUnitQuantityName.REST_MASS_OF_ELECTRON_REST_MASS_OF_PROTON_PLUS),
  MASS_EXCESS (252, EUnitQuantityName.MASS_EXCESS),
  MASS_DEFECT (253, EUnitQuantityName.MASS_DEFECT),
  RELATIVE_MASS_EXCESS_RELATIVE_MASS_DEFECT (254, EUnitQuantityName.RELATIVE_MASS_EXCESS_RELATIVE_MASS_DEFECT),
  PACKING_FRACTION_BINDING_FRACTION (255, EUnitQuantityName.PACKING_FRACTION_BINDING_FRACTION),
  PLANCK_CONSTANT (256, EUnitQuantityName.PLANCK_CONSTANT),
  BOHR_RADIUS (257, EUnitQuantityName.BOHR_RADIUS),
  RYDBERG_CONSTANT (258, EUnitQuantityName.RYDBERG_CONSTANT),
  HARTREE_ENERGY (259, EUnitQuantityName.HARTREE_ENERGY),
  MAGNETIC_MOMENT_OF_PARTICLE_BOHR_MAGNETON_PLUS (260, EUnitQuantityName.MAGNETIC_MOMENT_OF_PARTICLE_BOHR_MAGNETON_PLUS),
  GYROMAGNETIC_COEFFICIENT_GYROMAGNETIC_RATIO (261, EUnitQuantityName.GYROMAGNETIC_COEFFICIENT_GYROMAGNETIC_RATIO),
  G_FACTOR_OF_ATOM_OR_ELECTRON_G_FACTOR_OF_NUCLEUS (262, EUnitQuantityName.G_FACTOR_OF_ATOM_OR_ELECTRON_G_FACTOR_OF_NUCLEUS),
  LARMOR_ANGULAR_FREQUENCY (263, EUnitQuantityName.LARMOR_ANGULAR_FREQUENCY),
  NUCLEAR_PRECESSION_CYCLOTRON_ANGULAR_FREQUENCY (264, EUnitQuantityName.NUCLEAR_PRECESSION_CYCLOTRON_ANGULAR_FREQUENCY),
  NUCLEAR_QUADRUPOLE_MOMENT (265, EUnitQuantityName.NUCLEAR_QUADRUPOLE_MOMENT),
  NUCLEAR_RADIUS_ELECTRON_RADIUS_PLUS (266, EUnitQuantityName.NUCLEAR_RADIUS_ELECTRON_RADIUS_PLUS),
  ORBITAL_ANGULAR_MOMENTUM_QUANTUM_NUMBER (267, EUnitQuantityName.ORBITAL_ANGULAR_MOMENTUM_QUANTUM_NUMBER),
  SPIN_ANGULAR_MOMENTUM_QUANTUM_NUMBER (268, EUnitQuantityName.SPIN_ANGULAR_MOMENTUM_QUANTUM_NUMBER),
  TOTAL_ANGULAR_MOMENTUM_QUANTUM_NUMBER (269, EUnitQuantityName.TOTAL_ANGULAR_MOMENTUM_QUANTUM_NUMBER),
  NUCLEAR_SPIN_QUANTUM_NUMBER (270, EUnitQuantityName.NUCLEAR_SPIN_QUANTUM_NUMBER),
  HYPERFINE_STRUCTURE_QUANTUM_NUMBER (271, EUnitQuantityName.HYPERFINE_STRUCTURE_QUANTUM_NUMBER),
  PRINCIPLE_QUANTUM_NUMBER (272, EUnitQuantityName.PRINCIPLE_QUANTUM_NUMBER),
  MAGNETIC_QUANTUM_NUMBER (273, EUnitQuantityName.MAGNETIC_QUANTUM_NUMBER),
  FINE_STRUCTURE_CONSTANT (274, EUnitQuantityName.FINE_STRUCTURE_CONSTANT),
  MEAN_LIFE_HALF_LIFE (275, EUnitQuantityName.MEAN_LIFE_HALF_LIFE),
  LEVEL_WIDTH_ALPHA_DISINTEGRATION_ENERGY (276, EUnitQuantityName.LEVEL_WIDTH_ALPHA_DISINTEGRATION_ENERGY),
  MAXIMUM_BETA_PARTICLE_ENERGY_BETA_DISINTEGRATION_ENERGY (277, EUnitQuantityName.MAXIMUM_BETA_PARTICLE_ENERGY_BETA_DISINTEGRATION_ENERGY),
  INTERNAL_CONVERSION_FACTOR (278, EUnitQuantityName.INTERNAL_CONVERSION_FACTOR),
  ACTIVITY (279, EUnitQuantityName.ACTIVITY),
  SPECIFIC_ACTIVITY_IN_A_SAMPLE (280, EUnitQuantityName.SPECIFIC_ACTIVITY_IN_A_SAMPLE),
  VOLUMIC_ACTIVITY_ACTIVITY_CONCENTRATION (281, EUnitQuantityName.VOLUMIC_ACTIVITY_ACTIVITY_CONCENTRATION),
  DECAY_CONSTANT_DISINTEGRATION_CONSTANT (282, EUnitQuantityName.DECAY_CONSTANT_DISINTEGRATION_CONSTANT),
  REACTION_ENERGY (283, EUnitQuantityName.REACTION_ENERGY),
  RESONANCE_ENERGY (284, EUnitQuantityName.RESONANCE_ENERGY),
  AVERAGE_ENERGY_LOSS_PER_ION_PAIR_FORMED_PLUS (285, EUnitQuantityName.AVERAGE_ENERGY_LOSS_PER_ION_PAIR_FORMED_PLUS),
  CROSS_SECTION (286, EUnitQuantityName.CROSS_SECTION),
  TOTAL_CROSS_SECTION (287, EUnitQuantityName.TOTAL_CROSS_SECTION),
  ANGULAR_CROSS_SECTION (288, EUnitQuantityName.ANGULAR_CROSS_SECTION),
  SPECTRAL_CROSS_SECTION (289, EUnitQuantityName.SPECTRAL_CROSS_SECTION),
  SPECTRAL_ANGULAR_CROSS_SECTION (290, EUnitQuantityName.SPECTRAL_ANGULAR_CROSS_SECTION),
  MACROSCOPIC_CROSS_SECTION_VOLUMIC_CROSS_SECTION_PLUS (291, EUnitQuantityName.MACROSCOPIC_CROSS_SECTION_VOLUMIC_CROSS_SECTION_PLUS),
  PARTICLE_FLUENCE (292, EUnitQuantityName.PARTICLE_FLUENCE),
  PARTICLE_FLUENCE_RATE_PARTICAL_FLUX_DENSITY_PLUS (293, EUnitQuantityName.PARTICLE_FLUENCE_RATE_PARTICAL_FLUX_DENSITY_PLUS),
  ENERGY_FLUENCE (294, EUnitQuantityName.ENERGY_FLUENCE),
  ENERGY_FLUENCE_RATE_ENERGY_FLUX_DENSITY (295, EUnitQuantityName.ENERGY_FLUENCE_RATE_ENERGY_FLUX_DENSITY),
  LINEAR_ATTENUATION_COEFFICIENT (296, EUnitQuantityName.LINEAR_ATTENUATION_COEFFICIENT),
  MASS_ATTENUATION_COEFFICIENT (297, EUnitQuantityName.MASS_ATTENUATION_COEFFICIENT),
  MOLAR_ATTENUATION_COEFFICIENT (298, EUnitQuantityName.MOLAR_ATTENUATION_COEFFICIENT),
  ATOMIC_ATTENUATION_COEFFICIENT (299, EUnitQuantityName.ATOMIC_ATTENUATION_COEFFICIENT),
  SLOWING_DOWN_AREA_DIFFUSION_AREA_PLUS (300, EUnitQuantityName.SLOWING_DOWN_AREA_DIFFUSION_AREA_PLUS),
  HALF_THICKNESS_HALF_VALUE_THICKNESS (301, EUnitQuantityName.HALF_THICKNESS_HALF_VALUE_THICKNESS),
  MEAN_LINEAR_RANGE_MEAN_FREE_PATH (302, EUnitQuantityName.MEAN_LINEAR_RANGE_MEAN_FREE_PATH),
  SLOWING_DOWN_LENGTH_DIFFUSION_LENGTH_PLUS (303, EUnitQuantityName.SLOWING_DOWN_LENGTH_DIFFUSION_LENGTH_PLUS),
  NEUTRON_YIELD_PER_FISSION_NEUTRON_YIELD_PER_ABSORPTION (304, EUnitQuantityName.NEUTRON_YIELD_PER_FISSION_NEUTRON_YIELD_PER_ABSORPTION),
  FAST_FISSION_FACTOR (305, EUnitQuantityName.FAST_FISSION_FACTOR),
  THERMAL_UTILIZATION_FACTOR (306, EUnitQuantityName.THERMAL_UTILIZATION_FACTOR),
  NON_LEAKAGE_PROBABILITY (307, EUnitQuantityName.NON_LEAKAGE_PROBABILITY),
  MULTIPLICATION_FACTOR_INFINITE_MEDIUM_MULTIPLICATION_FACTOR_PLUS (308, EUnitQuantityName.MULTIPLICATION_FACTOR_INFINITE_MEDIUM_MULTIPLICATION_FACTOR_PLUS),
  DIFFUSION_COEFFICIENT_FOR_NEUTRON_FLUENCE_RATE_DIFFUSION_COEFFICIENT_FOR_NEUTRON_FLUX_DENSITY (309, EUnitQuantityName.DIFFUSION_COEFFICIENT_FOR_NEUTRON_FLUENCE_RATE_DIFFUSION_COEFFICIENT_FOR_NEUTRON_FLUX_DENSITY),
  TOTAL_LINEAR_STOPPING_POWER (310, EUnitQuantityName.TOTAL_LINEAR_STOPPING_POWER),
  TOTAL_ATOMIC_STOPPING_POWER (311, EUnitQuantityName.TOTAL_ATOMIC_STOPPING_POWER),
  TOTAL_MASS_STOPPING_POWER (312, EUnitQuantityName.TOTAL_MASS_STOPPING_POWER),
  MEAN_MASS_RANGE (313, EUnitQuantityName.MEAN_MASS_RANGE),
  LINEAR_IONIZATION_BY_A_PARTICLE_TOTAL_IONIZATION_BY_A_PARTICLE (314, EUnitQuantityName.LINEAR_IONIZATION_BY_A_PARTICLE_TOTAL_IONIZATION_BY_A_PARTICLE),
  MOBILITY (315, EUnitQuantityName.MOBILITY),
  ION_NUMBER_DENSITY_ION_DENSITY_PLUS (316, EUnitQuantityName.ION_NUMBER_DENSITY_ION_DENSITY_PLUS),
  RECOMBINATION_COEFFICIENT (317, EUnitQuantityName.RECOMBINATION_COEFFICIENT),
  NEUTRON_SPEED (318, EUnitQuantityName.NEUTRON_SPEED),
  DIFFUSION_COEFFICIENT_DIFFUSION_COEFFICIENT_FOR_NEUTRON_NUMBER_DENSITY (319, EUnitQuantityName.DIFFUSION_COEFFICIENT_DIFFUSION_COEFFICIENT_FOR_NEUTRON_NUMBER_DENSITY),
  NEUTRON_SOURCE_DENSITY (320, EUnitQuantityName.NEUTRON_SOURCE_DENSITY),
  SLOWING_DOWN_DENSITY (321, EUnitQuantityName.SLOWING_DOWN_DENSITY),
  RESONANCE_ESCAPE_PROBABILITY (322, EUnitQuantityName.RESONANCE_ESCAPE_PROBABILITY),
  LETHARGY (323, EUnitQuantityName.LETHARGY),
  AVERAGE_LOGARITHMIC_ENERGY_DECREMENT (324, EUnitQuantityName.AVERAGE_LOGARITHMIC_ENERGY_DECREMENT),
  REACTIVITY (325, EUnitQuantityName.REACTIVITY),
  REACTOR_TIME_CONSTANT (326, EUnitQuantityName.REACTOR_TIME_CONSTANT),
  ENERGY_IMPARTED_MEAN_ENERGY_IMPARTED (327, EUnitQuantityName.ENERGY_IMPARTED_MEAN_ENERGY_IMPARTED),
  SPECIFIC_ENERGY_IMPARTED_MASSIC_ENERGY_IMPARTED (328, EUnitQuantityName.SPECIFIC_ENERGY_IMPARTED_MASSIC_ENERGY_IMPARTED),
  ABSORBED_DOSE (329, EUnitQuantityName.ABSORBED_DOSE),
  KERMA (330, EUnitQuantityName.KERMA),
  DOSE_EQUIVALENT (331, EUnitQuantityName.DOSE_EQUIVALENT),
  ABSORBED_DOSE_RATE (332, EUnitQuantityName.ABSORBED_DOSE_RATE),
  KERMA_RATE (333, EUnitQuantityName.KERMA_RATE),
  LINEAR_ENERGY_TRANSFER (334, EUnitQuantityName.LINEAR_ENERGY_TRANSFER),
  MASS_ENERGY_TRANSFER_COEFFICIENT (335, EUnitQuantityName.MASS_ENERGY_TRANSFER_COEFFICIENT),
  EXPOSURE (336, EUnitQuantityName.EXPOSURE),
  EXPOSURE_RATE (337, EUnitQuantityName.EXPOSURE_RATE),
  EQUIVALENCE_DOSE_OUTPUT (338, EUnitQuantityName.EQUIVALENCE_DOSE_OUTPUT),
  REYNOLDS_NUMBER (339, EUnitQuantityName.REYNOLDS_NUMBER),
  EULER_NUMBER (340, EUnitQuantityName.EULER_NUMBER),
  FROUDE_NUMBER (341, EUnitQuantityName.FROUDE_NUMBER),
  GRASHOF_NUMBER (342, EUnitQuantityName.GRASHOF_NUMBER),
  WEBER_NUMBER (343, EUnitQuantityName.WEBER_NUMBER),
  MACH_NUMBER (344, EUnitQuantityName.MACH_NUMBER),
  KNUDSEN_NUMBER (345, EUnitQuantityName.KNUDSEN_NUMBER),
  STROUHAL_NUMBER (346, EUnitQuantityName.STROUHAL_NUMBER),
  FOURIER_NUMBER (347, EUnitQuantityName.FOURIER_NUMBER),
  PECLET_NUMBER (348, EUnitQuantityName.PECLET_NUMBER),
  RAYLEIGH_NUMBER (349, EUnitQuantityName.RAYLEIGH_NUMBER),
  NUSSELT_NUMBER (350, EUnitQuantityName.NUSSELT_NUMBER),
  STANTON_NUMBER (351, EUnitQuantityName.STANTON_NUMBER),
  FOURIER_NUMBER_FOR_MASS_TRANSFER (352, EUnitQuantityName.FOURIER_NUMBER_FOR_MASS_TRANSFER),
  PECLET_NUMBER_FOR_MASS_TRANSFER (353, EUnitQuantityName.PECLET_NUMBER_FOR_MASS_TRANSFER),
  GRASHOF_NUMBER_FOR_MASS_TRANSFER (354, EUnitQuantityName.GRASHOF_NUMBER_FOR_MASS_TRANSFER),
  NUSSELT_NUMBER_FOR_MASS_TRANSFER (355, EUnitQuantityName.NUSSELT_NUMBER_FOR_MASS_TRANSFER),
  STANTON_NUMBER_FOR_MASS_TRANSFER (356, EUnitQuantityName.STANTON_NUMBER_FOR_MASS_TRANSFER),
  PRANDTL_NUMBER (357, EUnitQuantityName.PRANDTL_NUMBER),
  SCHMIDT_NUMBER (358, EUnitQuantityName.SCHMIDT_NUMBER),
  LEWIS_NUMBER (359, EUnitQuantityName.LEWIS_NUMBER),
  MAGNETIC_REYNOLDS_NUMBER (360, EUnitQuantityName.MAGNETIC_REYNOLDS_NUMBER),
  ALFVEN_NUMBER (361, EUnitQuantityName.ALFVEN_NUMBER),
  HARTMANN_NUMBER (362, EUnitQuantityName.HARTMANN_NUMBER),
  COWLING_NUMBER (363, EUnitQuantityName.COWLING_NUMBER),
  MOBILITY_RATIO (364, EUnitQuantityName.MOBILITY_RATIO),
  LATTICE_VECTOR_FUNDAMENTAL_LATTICE_VECTOR (365, EUnitQuantityName.LATTICE_VECTOR_FUNDAMENTAL_LATTICE_VECTOR),
  LATTICE_PLANE_SPACING_BURGERS_VECTOR (366, EUnitQuantityName.LATTICE_PLANE_SPACING_BURGERS_VECTOR),
  BRAGG_ANGLE (367, EUnitQuantityName.BRAGG_ANGLE),
  ORDER_OF_REFLEXION (368, EUnitQuantityName.ORDER_OF_REFLEXION),
  SHORT_RANGE_ORDER_PARAMETER_LONG_RANGE_ORDER_PARAMETER (369, EUnitQuantityName.SHORT_RANGE_ORDER_PARAMETER_LONG_RANGE_ORDER_PARAMETER),
  RELAXATION_TIME_CARRIER_LIFE_TIME (370, EUnitQuantityName.RELAXATION_TIME_CARRIER_LIFE_TIME),
  MAGNETIC_FLUX_QUANTUM (371, EUnitQuantityName.MAGNETIC_FLUX_QUANTUM),
  PARTICLE_POSITION_VECTOR_EQUILIBRIUM_POSITION_VECTOR_OF_ION_OR_ATOM_PLUS (372, EUnitQuantityName.PARTICLE_POSITION_VECTOR_EQUILIBRIUM_POSITION_VECTOR_OF_ION_OR_ATOM_PLUS),
  DEBYE_WALLE_FACTOR (373, EUnitQuantityName.DEBYE_WALLE_FACTOR),
  MEAN_FREE_PATH_OF_PHONONS_OR_ELECTRONS_LONDON_PENETRATION_DEPTH_PLUS (374, EUnitQuantityName.MEAN_FREE_PATH_OF_PHONONS_OR_ELECTRONS_LONDON_PENETRATION_DEPTH_PLUS),
  ANGULAR_REPETENCY_ANGULAR_WAVE_NUMBER (375, EUnitQuantityName.ANGULAR_REPETENCY_ANGULAR_WAVE_NUMBER),
  FERMI_ANGULAR_REPETENCY_FERMI_ANGULAR_WAVE_NUMBER (376, EUnitQuantityName.FERMI_ANGULAR_REPETENCY_FERMI_ANGULAR_WAVE_NUMBER),
  DEBYE_ANGULAR_REPETENCY_DEBYE_ANGULAR_WAVE_NUMBER (377, EUnitQuantityName.DEBYE_ANGULAR_REPETENCY_DEBYE_ANGULAR_WAVE_NUMBER),
  ANGULAR_RECIPROCAL_LATTICE_VECTOR_FUNDAMENTAL_RECIPROCAL_LATTICE_VECTOR (378, EUnitQuantityName.ANGULAR_RECIPROCAL_LATTICE_VECTOR_FUNDAMENTAL_RECIPROCAL_LATTICE_VECTOR),
  DEBYE_ANGULAR_FREQUENCY (379, EUnitQuantityName.DEBYE_ANGULAR_FREQUENCY),
  DEBYE_TEMPERATURE_CURIE_TEMPERATURE_PLUS (380, EUnitQuantityName.DEBYE_TEMPERATURE_CURIE_TEMPERATURE_PLUS),
  SPECTRAL_CONCENTRATION_OF_VIBRATIONAL_MODES_IN_TERMS_OF_ANGULAR_FREQUENCY (381, EUnitQuantityName.SPECTRAL_CONCENTRATION_OF_VIBRATIONAL_MODES_IN_TERMS_OF_ANGULAR_FREQUENCY),
  GR_NEISEN_PARAMETER (382, EUnitQuantityName.GR_NEISEN_PARAMETER),
  MADELUNG_CONSTANT (383, EUnitQuantityName.MADELUNG_CONSTANT),
  LANDAU_GINZBURG_NUMBER (384, EUnitQuantityName.LANDAU_GINZBURG_NUMBER),
  DENSITY_OF_STATES (385, EUnitQuantityName.DENSITY_OF_STATES),
  RESIDUAL_RESISTIVITY (386, EUnitQuantityName.RESIDUAL_RESISTIVITY),
  HALL_COEFFICIENT (387, EUnitQuantityName.HALL_COEFFICIENT),
  THERMOELECTROMOTIVE_FORCE_BETWEEN_SUBSTANCES_A_AND_B_PELTIER_COEFFICIENT_FOR_SUBSTANCES_A_AND_B (388, EUnitQuantityName.THERMOELECTROMOTIVE_FORCE_BETWEEN_SUBSTANCES_A_AND_B_PELTIER_COEFFICIENT_FOR_SUBSTANCES_A_AND_B),
  THERMODYNAMIC_CRITICAL_MAGNETIC_FLUX_DENSITY_LOWER_CRITICAL_MAGNETIC_FLUX_DENSITY_PLUS (389, EUnitQuantityName.THERMODYNAMIC_CRITICAL_MAGNETIC_FLUX_DENSITY_LOWER_CRITICAL_MAGNETIC_FLUX_DENSITY_PLUS),
  SEEBECK_COEFFICIENT_FOR_SUBSTANCES_A_AND_B (390, EUnitQuantityName.SEEBECK_COEFFICIENT_FOR_SUBSTANCES_A_AND_B),
  THOMPSON_COEFFICIENT (391, EUnitQuantityName.THOMPSON_COEFFICIENT),
  WORK_FUNCTION (392, EUnitQuantityName.WORK_FUNCTION),
  FERMI_ENERGY (393, EUnitQuantityName.FERMI_ENERGY),
  GAP_ENERGY (394, EUnitQuantityName.GAP_ENERGY),
  DONOR_IONIZATION_ENERGY_ACCEPTOR_IONIZATION_ENERGY_PLUS (395, EUnitQuantityName.DONOR_IONIZATION_ENERGY_ACCEPTOR_IONIZATION_ENERGY_PLUS),
  RICHARDSON_CONSTANT (396, EUnitQuantityName.RICHARDSON_CONSTANT),
  ELECTRON_NUMBER_DENSITY_VOLUMIC_ELECTRON_NUMBER_PLUS (397, EUnitQuantityName.ELECTRON_NUMBER_DENSITY_VOLUMIC_ELECTRON_NUMBER_PLUS),
  EFFECTIVE_MASS (398, EUnitQuantityName.EFFECTIVE_MASS),
  BURST_INDEX (399, EUnitQuantityName.BURST_INDEX),
  HARDNESS_INDEX (400, EUnitQuantityName.HARDNESS_INDEX),
  POROSITY (401, EUnitQuantityName.POROSITY);

  private final int m_nID;
  private final IHasDisplayText m_aName;

  private EUnitQuantity (@Nonnegative final int nNumber, @Nonnull final EUnitQuantityName aName)
  {
    m_nID = nNumber;
    m_aName = aName;
  }

  @Nonnegative
  public int getID ()
  {
    return m_nID;
  }

  @Nullable
  public String getDisplayText (@Nonnull final Locale aContentLocale)
  {
    return m_aName.getDisplayText (aContentLocale);
  }

  @Nullable
  public static EUnitQuantity getFromIDOrNull (final int nID)
  {
    return EnumHelper.getFromIDOrNull (EUnitQuantity.class, nID);
  }
}
