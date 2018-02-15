import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs/Observable';
import { SERVER_API_URL } from '../../app.constants';

import { WishListItemSuchaz } from './wish-list-item-suchaz.model';
import { createRequestOption } from '../../shared';

export type EntityResponseType = HttpResponse<WishListItemSuchaz>;

@Injectable()
export class WishListItemSuchazService {

    private resourceUrl =  SERVER_API_URL + 'api/wish-list-items';

    constructor(private http: HttpClient) { }

    create(wishListItem: WishListItemSuchaz): Observable<EntityResponseType> {
        const copy = this.convert(wishListItem);
        return this.http.post<WishListItemSuchaz>(this.resourceUrl, copy, { observe: 'response' })
            .map((res: EntityResponseType) => this.convertResponse(res));
    }

    update(wishListItem: WishListItemSuchaz): Observable<EntityResponseType> {
        const copy = this.convert(wishListItem);
        return this.http.put<WishListItemSuchaz>(this.resourceUrl, copy, { observe: 'response' })
            .map((res: EntityResponseType) => this.convertResponse(res));
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http.get<WishListItemSuchaz>(`${this.resourceUrl}/${id}`, { observe: 'response'})
            .map((res: EntityResponseType) => this.convertResponse(res));
    }

    query(req?: any): Observable<HttpResponse<WishListItemSuchaz[]>> {
        const options = createRequestOption(req);
        return this.http.get<WishListItemSuchaz[]>(this.resourceUrl, { params: options, observe: 'response' })
            .map((res: HttpResponse<WishListItemSuchaz[]>) => this.convertArrayResponse(res));
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response'});
    }

    private convertResponse(res: EntityResponseType): EntityResponseType {
        const body: WishListItemSuchaz = this.convertItemFromServer(res.body);
        return res.clone({body});
    }

    private convertArrayResponse(res: HttpResponse<WishListItemSuchaz[]>): HttpResponse<WishListItemSuchaz[]> {
        const jsonResponse: WishListItemSuchaz[] = res.body;
        const body: WishListItemSuchaz[] = [];
        for (let i = 0; i < jsonResponse.length; i++) {
            body.push(this.convertItemFromServer(jsonResponse[i]));
        }
        return res.clone({body});
    }

    /**
     * Convert a returned JSON object to WishListItemSuchaz.
     */
    private convertItemFromServer(wishListItem: WishListItemSuchaz): WishListItemSuchaz {
        const copy: WishListItemSuchaz = Object.assign({}, wishListItem);
        return copy;
    }

    /**
     * Convert a WishListItemSuchaz to a JSON which can be sent to the server.
     */
    private convert(wishListItem: WishListItemSuchaz): WishListItemSuchaz {
        const copy: WishListItemSuchaz = Object.assign({}, wishListItem);
        return copy;
    }
}
