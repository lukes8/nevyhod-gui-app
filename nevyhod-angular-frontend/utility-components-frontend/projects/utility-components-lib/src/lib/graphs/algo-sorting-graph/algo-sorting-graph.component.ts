import { Component, OnInit } from '@angular/core';
import { SorterFactory } from '../../util/sorter-factory';

@Component({
  selector: 'lib-algo-sorting-graph',
  templateUrl: './algo-sorting-graph.component.html',
  styleUrls: ['./algo-sorting-graph.component.css']
})
export class AlgoSortingGraphComponent implements OnInit {
  values: number[] = [];
  maxSize: number = 30;

  ngOnInit(): void {

  }
  constructor() {
    this.reset();
  }

  sort() {
    let sorter = SorterFactory.makeSorter();
    this.values = sorter.sort(this.values);
    console.log("sorted");
  }

  reset() {
    this.values = [];
    for (let i = 1; i <= this.maxSize; i++) {
      this.values.push(i);
    }
    this.values = this.shuffle(this.values);
  }

  shuffle(values: number[]): number[] {
    let currentIndex = values.length;
    let i = 0;
    let randomValues: number[] = [];
    let done = false;
    while (!done) {
      let randomIndex = Math.floor(Math.random() * currentIndex);
      // console.log(values[randomIndex]);
      if (!randomValues.includes(values[randomIndex])) {
        randomValues.push(values[randomIndex]);
      }
      if (randomValues.length == currentIndex) {
        done = true;
      }
    }
    for (i = 0; i < randomValues.length; i++) {
      console.log(randomValues[i]);
    }
    console.log('done shuffle')
    return randomValues;
  }


}
