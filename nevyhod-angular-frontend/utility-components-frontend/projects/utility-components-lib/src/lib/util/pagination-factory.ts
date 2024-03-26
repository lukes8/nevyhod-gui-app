import { Observable, delay, map, of, tap } from "rxjs";
import { ItemVO } from "../factory/item-model";

export interface IServerResponse {
    items: ItemVO[];
    total: number;
    page: number;
    perPage: number;
}

export interface IPagination {
    getPage(page: number): Observable<IServerResponse>;
    total: number;
    p: number;
    items: ItemVO[];
}

export class Pageable {
    page: number;
    perPage: number;
    sort: string[];
}

export class PageableFactory {
    static makeDefault(): Pageable {
        return {
            page: 0,
            perPage: 3,
            sort: []
        }
    }
    static makeObject(page: number, perPage: number, sort: string[]): Pageable {
        return {
            page: page,
            perPage: perPage,
            sort: sort
        }
    }
    static makeObject4UrlParams(pageable: Pageable): URLSearchParams {
        let params = new URLSearchParams();
        params.append("page", pageable.page.toString());
        params.append("size", pageable.perPage.toString());
        if (pageable.sort.length > 0) {
            params.append("sort", pageable.sort[0]);
        }
        return params;
    }
}

export class ServerResponseFactory {
    static makeEmpty(): IServerResponse {
        return {
            items: [],
            page: 1,
            total: 0,
            perPage: 4
        };
    }
    static makeObject(items: any[], page: number, total: number, perPage: number): IServerResponse {
        return {
            items: items,
            page: page,
            total: total,
            perPage: perPage
        };
    }
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
     * Simulator for server response within array of items
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
            page: page,
            perPage: 4
        }).pipe(delay(1000));
    }

    static sliceItems<T>(items: T[], page: number, perPage: number): Array<T> {
        const start = (page - 1) * perPage;
        const end = page * perPage;
        return items.slice(start, end);
    }

}