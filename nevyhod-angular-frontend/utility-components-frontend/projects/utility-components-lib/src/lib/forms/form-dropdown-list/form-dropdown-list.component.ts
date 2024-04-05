import { Component } from '@angular/core';

export class CategoryItem {
  title: string;
  touched: boolean;
  styleIcon: string;
  childs: CategoryItem[];

}

@Component({
  selector: 'lib-form-dropdown-list',
  templateUrl: './form-dropdown-list.component.html',
  styleUrls: ['../forms.scss']
})
export class FormDropdownListComponent {

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
