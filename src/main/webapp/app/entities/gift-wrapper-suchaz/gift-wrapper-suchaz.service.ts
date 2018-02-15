import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs/Observable';
import { SERVER_API_URL } from '../../app.constants';

import { GiftWrapperSuchaz } from './gift-wrapper-suchaz.model';
import { createRequestOption } from '../../shared';

export type EntityResponseType = HttpResponse<GiftWrapperSuchaz>;

@Injectable()
export class GiftWrapperSuchazService {

    private resourceUrl =  SERVER_API_URL + 'api/gift-wrappers';

    constructor(private http: HttpClient) { }

    create(giftWrapper: GiftWrapperSuchaz): Observable<EntityResponseType> {
        const copy = this.convert(giftWrapper);
        return this.http.post<GiftWrapperSuchaz>(this.resourceUrl, copy, { observe: 'response' })
            .map((res: EntityResponseType) => this.convertResponse(res));
    }

    update(giftWrapper: GiftWrapperSuchaz): Observable<EntityResponseType> {
        const copy = this.convert(giftWrapper);
        return this.http.put<GiftWrapperSuchaz>(this.resourceUrl, copy, { observe: 'response' })
            .map((res: EntityResponseType) => this.convertResponse(res));
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http.get<GiftWrapperSuchaz>(`${this.resourceUrl}/${id}`, { observe: 'response'})
            .map((res: EntityResponseType) => this.convertResponse(res));
    }

    query(req?: any): Observable<HttpResponse<GiftWrapperSuchaz[]>> {
        const options = createRequestOption(req);
        return this.http.get<GiftWrapperSuchaz[]>(this.resourceUrl, { params: options, observe: 'response' })
            .map((res: HttpResponse<GiftWrapperSuchaz[]>) => this.convertArrayResponse(res));
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response'});
    }

    private convertResponse(res: EntityResponseType): EntityResponseType {
        const body: GiftWrapperSuchaz = this.convertItemFromServer(res.body);
        return res.clone({body});
    }

    private convertArrayResponse(res: HttpResponse<GiftWrapperSuchaz[]>): HttpResponse<GiftWrapperSuchaz[]> {
        const jsonResponse: GiftWrapperSuchaz[] = res.body;
        const body: GiftWrapperSuchaz[] = [];
        for (let i = 0; i < jsonResponse.length; i++) {
            body.push(this.convertItemFromServer(jsonResponse[i]));
        }
        return res.clone({body});
    }

    /**
     * Convert a returned JSON object to GiftWrapperSuchaz.
     */
    private convertItemFromServer(giftWrapper: GiftWrapperSuchaz): GiftWrapperSuchaz {
        const copy: GiftWrapperSuchaz = Object.assign({}, giftWrapper);
        return copy;
    }

    /**
     * Convert a GiftWrapperSuchaz to a JSON which can be sent to the server.
     */
    private convert(giftWrapper: GiftWrapperSuchaz): GiftWrapperSuchaz {
        const copy: GiftWrapperSuchaz = Object.assign({}, giftWrapper);
        return copy;
    }
}
