import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs/Observable';
import { SERVER_API_URL } from '../../app.constants';

import { WishListSuchaz } from './wish-list-suchaz.model';
import { createRequestOption } from '../../shared';

export type EntityResponseType = HttpResponse<WishListSuchaz>;

@Injectable()
export class WishListSuchazService {

    private resourceUrl =  SERVER_API_URL + 'api/wish-lists';

    constructor(private http: HttpClient) { }

    create(wishList: WishListSuchaz): Observable<EntityResponseType> {
        const copy = this.convert(wishList);
        return this.http.post<WishListSuchaz>(this.resourceUrl, copy, { observe: 'response' })
            .map((res: EntityResponseType) => this.convertResponse(res));
    }

    update(wishList: WishListSuchaz): Observable<EntityResponseType> {
        const copy = this.convert(wishList);
        return this.http.put<WishListSuchaz>(this.resourceUrl, copy, { observe: 'response' })
            .map((res: EntityResponseType) => this.convertResponse(res));
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http.get<WishListSuchaz>(`${this.resourceUrl}/${id}`, { observe: 'response'})
            .map((res: EntityResponseType) => this.convertResponse(res));
    }

    query(req?: any): Observable<HttpResponse<WishListSuchaz[]>> {
        const options = createRequestOption(req);
        return this.http.get<WishListSuchaz[]>(this.resourceUrl, { params: options, observe: 'response' })
            .map((res: HttpResponse<WishListSuchaz[]>) => this.convertArrayResponse(res));
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response'});
    }

    private convertResponse(res: EntityResponseType): EntityResponseType {
        const body: WishListSuchaz = this.convertItemFromServer(res.body);
        return res.clone({body});
    }

    private convertArrayResponse(res: HttpResponse<WishListSuchaz[]>): HttpResponse<WishListSuchaz[]> {
        const jsonResponse: WishListSuchaz[] = res.body;
        const body: WishListSuchaz[] = [];
        for (let i = 0; i < jsonResponse.length; i++) {
            body.push(this.convertItemFromServer(jsonResponse[i]));
        }
        return res.clone({body});
    }

    /**
     * Convert a returned JSON object to WishListSuchaz.
     */
    private convertItemFromServer(wishList: WishListSuchaz): WishListSuchaz {
        const copy: WishListSuchaz = Object.assign({}, wishList);
        return copy;
    }

    /**
     * Convert a WishListSuchaz to a JSON which can be sent to the server.
     */
    private convert(wishList: WishListSuchaz): WishListSuchaz {
        const copy: WishListSuchaz = Object.assign({}, wishList);
        return copy;
    }
}
