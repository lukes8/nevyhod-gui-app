import { HttpClient, HttpClientModule } from '@angular/common/http';
import { Component } from '@angular/core';
import { AgGridAngular } from 'ag-grid-angular'; // AG Grid Component
import { ColDef, ColTypeDef, GridReadyEvent } from 'ag-grid-community'; // Column Definition Type Interface
import { getData } from './data';
import { IOlympicData } from './interfaces';
import { getData4OlympicWinners } from './data-olympic-winners';

@Component({
  selector: 'lib-table-grid-basic',
  standalone: true,
  imports: [AgGridAngular],
  templateUrl: './table-grid-basic.component.html',
  styleUrls: ['./table-grid-basic.component.scss'],
})
export class TableGridBasicComponent {
  // Row Data: The data to be displayed.

  // Column Definitions: Defines the columns to be displayed.

  themeClass: string = "ag-theme-quartz ag-theme-custom";
  // themeClass: string = "ag-theme-quartz-dark";

  columnDefs: ColDef[] = [
    { field: 'name', minWidth: 150 },
    { field: 'medals.gold', headerName: 'Gold', minWidth: 150 },
    { field: 'person.age', minWidth: 150 },
  ];

  columnDefs2: ColDef[] = [
    // using default ColDef
    { field: 'athlete', minWidth: 150 },
    { field: 'sport', minWidth: 150 },
    // using number column type
    { field: 'age', type: 'numberColumn', minWidth: 150 },
    { field: 'year', type: 'numberColumn', minWidth: 150 },
    // using date and non-editable column types
    { field: 'date', type: ['dateColumn', 'nonEditableColumn'], minWidth: 150 },
  ];

  public columnTypes: {
    [key: string]: ColTypeDef;
  } = {
      numberColumn: { width: 130, filter: 'agNumberColumnFilter' },
      medalColumn: { width: 100, columnGroupShow: 'open', filter: false },
      nonEditableColumn: { editable: false },
      dateColumn: {
        // specify we want to use the date filter
        filter: 'agDateColumnFilter'
      },
    };
  public defaultColDef: ColDef = {
    // set the default column width
    width: 150,
    // make every column editable
    editable: true,
    // make every column use 'text' filter by default
    filter: 'agTextColumnFilter',
    // enable floating filters by default
    floatingFilter: true,
    // disable cell data types
    cellDataType: false,
  };

  colDefs: ColDef[] = this.columnDefs2;
  rowData: any[] | null = getData4OlympicWinners();


  constructor(private _http: HttpClient) { }

  onGridReady(params: GridReadyEvent<IOlympicData>) {
    this._http
      .get<IOlympicData[]>(
        'https://www.ag-grid.com/example-assets/olympic-winners.json'
      )
      .subscribe((data) => (this.rowData = data));
  }
}
