import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';

import { Observable } from 'rxjs/Observable';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { BuyLaterListSuchaz } from './buy-later-list-suchaz.model';
import { BuyLaterListSuchazPopupService } from './buy-later-list-suchaz-popup.service';
import { BuyLaterListSuchazService } from './buy-later-list-suchaz.service';
import { SuchAzUserSuchaz, SuchAzUserSuchazService } from '../such-az-user-suchaz';

@Component({
    selector: 'jhi-buy-later-list-suchaz-dialog',
    templateUrl: './buy-later-list-suchaz-dialog.component.html'
})
export class BuyLaterListSuchazDialogComponent implements OnInit {

    buyLaterList: BuyLaterListSuchaz;
    isSaving: boolean;

    suchazusers: SuchAzUserSuchaz[];

    constructor(
        public activeModal: NgbActiveModal,
        private jhiAlertService: JhiAlertService,
        private buyLaterListService: BuyLaterListSuchazService,
        private suchAzUserService: SuchAzUserSuchazService,
        private eventManager: JhiEventManager
    ) {
    }

    ngOnInit() {
        this.isSaving = false;
        this.suchAzUserService
            .query({filter: 'buylaterlist-is-null'})
            .subscribe((res: HttpResponse<SuchAzUserSuchaz[]>) => {
                if (!this.buyLaterList.suchAzUserId) {
                    this.suchazusers = res.body;
                } else {
                    this.suchAzUserService
                        .find(this.buyLaterList.suchAzUserId)
                        .subscribe((subRes: HttpResponse<SuchAzUserSuchaz>) => {
                            this.suchazusers = [subRes.body].concat(res.body);
                        }, (subRes: HttpErrorResponse) => this.onError(subRes.message));
                }
            }, (res: HttpErrorResponse) => this.onError(res.message));
    }

    clear() {
        this.activeModal.dismiss('cancel');
    }

    save() {
        this.isSaving = true;
        if (this.buyLaterList.id !== undefined) {
            this.subscribeToSaveResponse(
                this.buyLaterListService.update(this.buyLaterList));
        } else {
            this.subscribeToSaveResponse(
                this.buyLaterListService.create(this.buyLaterList));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<BuyLaterListSuchaz>>) {
        result.subscribe((res: HttpResponse<BuyLaterListSuchaz>) =>
            this.onSaveSuccess(res.body), (res: HttpErrorResponse) => this.onSaveError());
    }

    private onSaveSuccess(result: BuyLaterListSuchaz) {
        this.eventManager.broadcast({ name: 'buyLaterListListModification', content: 'OK'});
        this.isSaving = false;
        this.activeModal.dismiss(result);
    }

    private onSaveError() {
        this.isSaving = false;
    }

    private onError(error: any) {
        this.jhiAlertService.error(error.message, null, null);
    }

    trackSuchAzUserById(index: number, item: SuchAzUserSuchaz) {
        return item.id;
    }
}

@Component({
    selector: 'jhi-buy-later-list-suchaz-popup',
    template: ''
})
export class BuyLaterListSuchazPopupComponent implements OnInit, OnDestroy {

    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private buyLaterListPopupService: BuyLaterListSuchazPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            if ( params['id'] ) {
                this.buyLaterListPopupService
                    .open(BuyLaterListSuchazDialogComponent as Component, params['id']);
            } else {
                this.buyLaterListPopupService
                    .open(BuyLaterListSuchazDialogComponent as Component);
            }
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
