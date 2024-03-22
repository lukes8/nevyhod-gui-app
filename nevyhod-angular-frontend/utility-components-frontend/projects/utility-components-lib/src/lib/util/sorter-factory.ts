import { Inject, Injectable } from '@angular/core';

abstract class AbstractSortStrategy {
    delay: number = 100;

    abstract sort(array: number[]): number[];

    sleep(ms: number) {
        return new Promise((resolve) => setTimeout(resolve, ms));
    }
}

class BubbleSortStrategy extends AbstractSortStrategy {
    sort(array: number[]): number[] {
        console.log('bubble sort');
        const sortStrategy = async () => {
            for (let i = 0; i < array.length; i++) {
                for (let j = 0; j < array.length - 1; j++) {
                    const mainElement = array[j];
                    const nextElement = array[j + 1];
                    if (mainElement > nextElement) {
                        array = swap(array, j, j + 1);
                    }
                }
                if (true) {
                    await this.sleep(this.delay);
                }
            }
            console.log(array)
        }
        sortStrategy();
        return array;
    }
}
class SelectionSortStrategy extends AbstractSortStrategy {
    sort(array: number[]): number[] {
        console.log('selection sort');
        const sortStrategy = async () => {
            for (let i = 0; i < array.length; i++) {
                let idx4MinVal = i;
                for (let j = i + 1; j < array.length; j++) {
                    const element = array[j];
                    if (array[idx4MinVal] > array[j]) {
                        idx4MinVal = j;
                    }
                }
                array = swap(array, i, idx4MinVal);
                if (true) {
                    await this.sleep(this.delay);
                }
            }
        }
        sortStrategy();
        return array;
    }
}

class Sorter {
    constructor(private _sorter4SmallNbrs: AbstractSortStrategy, private _sorter4BigNbrs: AbstractSortStrategy) {
    }

    sort(array: number[]): number[] {
        if (array.length > 30) {
            return this._sorter4BigNbrs.sort(array);
        } else {
            return this._sorter4SmallNbrs.sort(array);
        }
    }
}

function swap(array: number[], firstIdx: number, lastIdx: number): number[] {
    let temp: number = array[firstIdx];
    array[firstIdx] = array[lastIdx];
    array[lastIdx] = temp;
    return array;
}

export class SorterFactory {
    static makeSorter(): Sorter {
        return new Sorter(new BubbleSortStrategy(), new SelectionSortStrategy());
    }
    // static makeSorter2(): Sorter {
    //     return new Sorter(new BubbleSortStrategy(), new QuickSortStrategy());
    // }
}