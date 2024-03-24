import { Observable, delay, map, of, tap } from "rxjs";
import { ItemVO } from "../factory/item-model";

export interface IServerResponse {
    items: ItemVO[];
    total: number;
    page: number;
}

export interface IPagination {
    getPage(page: number): Observable<IServerResponse>;
    total: number;
    p: number;
    items: ItemVO[];
}

export class PaginationHelperFactory {
    static makeInstance(items: ItemVO[], page: number, total: number, loading: boolean): PaginationHelper {
        return new PaginationHelper(items, page, total, loading);
    }
}

export class PaginationHelper implements IPagination {
    items: ItemVO[];
    asyncItems$: Observable<ItemVO[]>;
    itemsArray: ItemVO[];
    p: number = 1;
    total: number;
    loading: boolean = false;

    constructor(items: ItemVO[], page: number, total: number, loading: boolean) {
        this.items = items;
        this.p = page;
        this.total = total;
        this.loading = loading;
    }

    getPage(page: number): Observable<IServerResponse> {
        return this.serverCall(this.items, page);
    }

    /**
       * Non async approach
       * @param page 
       */
    getPage3(page: number) {
        this.loading = true;
        this.serverCall(this.items, page)
            .subscribe(res => {
                this.itemsArray = res.items;
                this.total = res.total;
                this.p = page;
                this.loading = false;
            });
    }

    /**
     * Simulator for server response
     * @param items 
     * @param page 
     * @returns 
     */
    serverCall(items: ItemVO[], page: number): Observable<IServerResponse> {
        const perPage = 4;
        const start = (page - 1) * perPage;
        const end = start + perPage;

        console.log('green ' + items.length)

        return of({
            items: items.slice(start, end),
            total: items.length,
            page: page
        }).pipe(delay(1000));
    }

}