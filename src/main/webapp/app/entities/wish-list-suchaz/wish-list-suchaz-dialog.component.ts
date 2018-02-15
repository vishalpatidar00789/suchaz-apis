import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';

import { Observable } from 'rxjs/Observable';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { WishListSuchaz } from './wish-list-suchaz.model';
import { WishListSuchazPopupService } from './wish-list-suchaz-popup.service';
import { WishListSuchazService } from './wish-list-suchaz.service';
import { SuchAzUserSuchaz, SuchAzUserSuchazService } from '../such-az-user-suchaz';

@Component({
    selector: 'jhi-wish-list-suchaz-dialog',
    templateUrl: './wish-list-suchaz-dialog.component.html'
})
export class WishListSuchazDialogComponent implements OnInit {

    wishList: WishListSuchaz;
    isSaving: boolean;

    suchazusers: SuchAzUserSuchaz[];

    constructor(
        public activeModal: NgbActiveModal,
        private jhiAlertService: JhiAlertService,
        private wishListService: WishListSuchazService,
        private suchAzUserService: SuchAzUserSuchazService,
        private eventManager: JhiEventManager
    ) {
    }

    ngOnInit() {
        this.isSaving = false;
        this.suchAzUserService
            .query({filter: 'wishlist-is-null'})
            .subscribe((res: HttpResponse<SuchAzUserSuchaz[]>) => {
                if (!this.wishList.suchAzUserId) {
                    this.suchazusers = res.body;
                } else {
                    this.suchAzUserService
                        .find(this.wishList.suchAzUserId)
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
        if (this.wishList.id !== undefined) {
            this.subscribeToSaveResponse(
                this.wishListService.update(this.wishList));
        } else {
            this.subscribeToSaveResponse(
                this.wishListService.create(this.wishList));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<WishListSuchaz>>) {
        result.subscribe((res: HttpResponse<WishListSuchaz>) =>
            this.onSaveSuccess(res.body), (res: HttpErrorResponse) => this.onSaveError());
    }

    private onSaveSuccess(result: WishListSuchaz) {
        this.eventManager.broadcast({ name: 'wishListListModification', content: 'OK'});
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
    selector: 'jhi-wish-list-suchaz-popup',
    template: ''
})
export class WishListSuchazPopupComponent implements OnInit, OnDestroy {

    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private wishListPopupService: WishListSuchazPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            if ( params['id'] ) {
                this.wishListPopupService
                    .open(WishListSuchazDialogComponent as Component, params['id']);
            } else {
                this.wishListPopupService
                    .open(WishListSuchazDialogComponent as Component);
            }
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
