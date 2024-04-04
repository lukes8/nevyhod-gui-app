import { Component } from '@angular/core';
import { IServerResponse, PaginationHelper } from 'projects/utility-components-lib/src/lib/util/pagination-factory';
import { BehaviorSubject, Observable, delay, of } from 'rxjs';
import { ItemFactory, ItemVO } from 'utility-components-lib';


@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent {
  title = 'utility-components-frontend';

  items: ItemVO[] = ItemFactory.makeDefault4List();
  subject: BehaviorSubject<IServerResponse> = new BehaviorSubject<IServerResponse>(
    {
      items: [],
      page: 1,
      total: 0,
      perPage: 4
    }
  );
  visibility4CategoryList: boolean = false;
  categoryItem: string;
  categoryItems: CategoryItem[] = [
    {
      title: 'Vsechno',
      touched: false,
      styleIcon: '',
      childs: []
    }
    , {
      title: 'Auto',
      touched: false,
      styleIcon: 'fa-car',
      childs: [
        {
          title: 'Cela kategorie',
          touched: false,
          styleIcon: '',
          childs: []
        },
        {
          title: 'Automobily',
          touched: false,
          styleIcon: '',
          childs: []
        }
      ]
    },
    {
      title: 'Auto',
      touched: false,
      styleIcon: 'fa-car',
      childs: [
        {
          title: 'Cela kategorie',
          touched: false,
          styleIcon: '',
          childs: []
        },
        {
          title: 'Automobily',
          touched: false,
          styleIcon: '',
          childs: []
        }
      ]
    },
    {
      title: 'Auto',
      touched: false,
      styleIcon: 'fa-car',
      childs: [
        {
          title: 'Cela kategorie',
          touched: false,
          styleIcon: '',
          childs: []
        },
        {
          title: 'Automobily',
          touched: false,
          styleIcon: '',
          childs: []
        }
      ]
    },
    {
      title: 'Auto',
      touched: false,
      styleIcon: 'fa-car',
      childs: [
        {
          title: 'Cela kategorie',
          touched: false,
          styleIcon: '',
          childs: []
        },
        {
          title: 'Automobily',
          touched: false,
          styleIcon: '',
          childs: []
        }
      ]
    },
    {
      title: 'Auto',
      touched: false,
      styleIcon: 'fa-car',
      childs: [
        {
          title: 'Cela kategorie',
          touched: false,
          styleIcon: '',
          childs: []
        },
        {
          title: 'Automobily',
          touched: false,
          styleIcon: '',
          childs: []
        }
      ]
    },
    {
      title: 'Chovatelstvi',
      touched: false,
      styleIcon: 'fa-paw',
      childs: []
    },
    {
      title: 'Potraviny',
      touched: false,
      styleIcon: 'fa-utensils',
      childs: []
    },
    {
      title: 'Potraviny',
      touched: false,
      styleIcon: 'fa-utensils',
      childs: []
    },
    {
      title: 'Potraviny',
      touched: false,
      styleIcon: 'fa-utensils',
      childs: []
    },
    {
      title: 'Potraviny',
      touched: false,
      styleIcon: 'fa-utensils',
      childs: []
    },

  ];


  constructor() {
    this.subject.next({
      items: PaginationHelper.sliceItems(ItemFactory.makeDefault4List(), 1, 4),
      page: 1,
      total: ItemFactory.makeDefault4List().length,
      perPage: 4
    })
  }

  onPageChangedEvent(page: number) {

    of("dummy observable timer").pipe(delay(1000)).subscribe(r => {
      console.log(r);
      this.subject.next({
        items: PaginationHelper.sliceItems(ItemFactory.makeDefault4List(), page, 4),
        page: page,
        total: ItemFactory.makeDefault4List().length,
        perPage: 4
      })
    });
  }

  onCategoryList() {
    this.visibility4CategoryList = true;
  }

  onCategoryItem(item: CategoryItem, useTouched: boolean) {
    let touched = !item.touched;
    console.log(touched === item.touched)
    if (useTouched && this.hasChilds(item)) {
      this.resetItems4Touched(item);
      console.log(item.touched);
      item.touched = touched;
      console.log(item.touched);
    } else {
      console.log(item.title);
      this.categoryItem = item.title;
      this.visibility4CategoryList = false;
    }
  }

  onClickOutside() {
    this.visibility4CategoryList = false;
  }

  resetItems4Touched(item: CategoryItem) {
    this.categoryItems.forEach(i => {
      i.touched = false;
      if (item === i) {
        console.log('here I am. The same reference. Everything is an object (except of some exceptions)');
      }
    })
  }
  hasChilds(item: CategoryItem): boolean {
    return item.childs !== undefined && item.childs.length > 0;
  }
}

export class CategoryItem {
  title: string;
  touched: boolean;
  styleIcon: string;
  childs: CategoryItem[];

}