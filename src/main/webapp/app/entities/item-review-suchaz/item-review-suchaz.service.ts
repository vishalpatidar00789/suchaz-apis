import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs/Observable';
import { SERVER_API_URL } from '../../app.constants';

import { ItemReviewSuchaz } from './item-review-suchaz.model';
import { createRequestOption } from '../../shared';

export type EntityResponseType = HttpResponse<ItemReviewSuchaz>;

@Injectable()
export class ItemReviewSuchazService {

    private resourceUrl =  SERVER_API_URL + 'api/item-reviews';

    constructor(private http: HttpClient) { }

    create(itemReview: ItemReviewSuchaz): Observable<EntityResponseType> {
        const copy = this.convert(itemReview);
        return this.http.post<ItemReviewSuchaz>(this.resourceUrl, copy, { observe: 'response' })
            .map((res: EntityResponseType) => this.convertResponse(res));
    }

    update(itemReview: ItemReviewSuchaz): Observable<EntityResponseType> {
        const copy = this.convert(itemReview);
        return this.http.put<ItemReviewSuchaz>(this.resourceUrl, copy, { observe: 'response' })
            .map((res: EntityResponseType) => this.convertResponse(res));
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http.get<ItemReviewSuchaz>(`${this.resourceUrl}/${id}`, { observe: 'response'})
            .map((res: EntityResponseType) => this.convertResponse(res));
    }

    query(req?: any): Observable<HttpResponse<ItemReviewSuchaz[]>> {
        const options = createRequestOption(req);
        return this.http.get<ItemReviewSuchaz[]>(this.resourceUrl, { params: options, observe: 'response' })
            .map((res: HttpResponse<ItemReviewSuchaz[]>) => this.convertArrayResponse(res));
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response'});
    }

    private convertResponse(res: EntityResponseType): EntityResponseType {
        const body: ItemReviewSuchaz = this.convertItemFromServer(res.body);
        return res.clone({body});
    }

    private convertArrayResponse(res: HttpResponse<ItemReviewSuchaz[]>): HttpResponse<ItemReviewSuchaz[]> {
        const jsonResponse: ItemReviewSuchaz[] = res.body;
        const body: ItemReviewSuchaz[] = [];
        for (let i = 0; i < jsonResponse.length; i++) {
            body.push(this.convertItemFromServer(jsonResponse[i]));
        }
        return res.clone({body});
    }

    /**
     * Convert a returned JSON object to ItemReviewSuchaz.
     */
    private convertItemFromServer(itemReview: ItemReviewSuchaz): ItemReviewSuchaz {
        const copy: ItemReviewSuchaz = Object.assign({}, itemReview);
        return copy;
    }

    /**
     * Convert a ItemReviewSuchaz to a JSON which can be sent to the server.
     */
    private convert(itemReview: ItemReviewSuchaz): ItemReviewSuchaz {
        const copy: ItemReviewSuchaz = Object.assign({}, itemReview);
        return copy;
    }
}
