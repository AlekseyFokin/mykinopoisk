/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package business;

import command.SaveToCSV;
import command.ActionCommand;
import command.SearchMovieByTitle;
import command.EmptyCommand;
import command.SaveToDB;

/**
 *
 * @author Фокин
 */
public enum CommandEnum {
    searchmovies{   
                                {this.command=new SearchMovieByTitle();}
                               },
    savetocsv{
                         {this.command=new SaveToCSV();}
                       },
    savetodb{
                       {this.command=new SaveToDB();}
                      },
    gotosearch{{this.command=new EmptyCommand();}}
    
    ;
    
    
    ActionCommand command;
    public ActionCommand getCurrentCommand()
    {
        return command;
    }
    
}
