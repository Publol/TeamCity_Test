Feature: Critical Scope


  Background: Login into TeamCity server
    Given As user I wish to login into TeamCity


  Scenario Outline: Add empty project to TeamCity server
   Given When User open create project menu
    When User create new project with <Project_Name> project name and <Build_Name> build name
    Then Project with <Project_Name> should be displayed on main page
  Examples:
    | Project_Name | Build_Name |
    | "Create project" | "Build" |

   Scenario Outline: Delete existing TeamCity project
     Given User should find <Project_Name> existing project
     Then User should have possibility to delete project
     And Project <Project_Name> should be successfully deleted
   Examples:
     | Project_Name |
     | "Create project" |

   Scenario Outline: Validate Build History Is Displayed
    Given When User open create project menu
     When User create new project with <Project_Name> project name and <Build_Name> build name
     And Select "Maven" build step
     Then Project with <Project_Name> should be displayed on main page

     When User click run build on <Project_Name> project and wait until build <Build_Name> will be completed in <Timeout> seconds
     Then Build history for <Project_Name> project and <Build_Name> build should display build current build history
     And User should find <Project_Name> existing project
     And User should have possibility to delete project
     And Project <Project_Name> should be successfully deleted
   Examples:
     | Project_Name | Build_Name | Timeout |
     | "Validate history project" | "Build" | 100 |

