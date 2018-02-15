import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';

import { Observable } from 'rxjs/Observable';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { SuchAzUserSuchaz } from './such-az-user-suchaz.model';
import { SuchAzUserSuchazPopupService } from './such-az-user-suchaz-popup.service';
import { SuchAzUserSuchazService } from './such-az-user-suchaz.service';
import { TraitSuchaz, TraitSuchazService } from '../trait-suchaz';
import { UserProfileSuchaz, UserProfileSuchazService } from '../user-profile-suchaz';
import { WishListSuchaz, WishListSuchazService } from '../wish-list-suchaz';
import { BuyLaterListSuchaz, BuyLaterListSuchazService } from '../buy-later-list-suchaz';
import { ActivityListSuchaz, ActivityListSuchazService } from '../activity-list-suchaz';

@Component({
    selector: 'jhi-such-az-user-suchaz-dialog',
    templateUrl: './such-az-user-suchaz-dialog.component.html'
})
export class SuchAzUserSuchazDialogComponent implements OnInit {

    suchAzUser: SuchAzUserSuchaz;
    isSaving: boolean;

    traits: TraitSuchaz[];

    userprofiles: UserProfileSuchaz[];

    wishlists: WishListSuchaz[];

    buylaterlists: BuyLaterListSuchaz[];

    activitylists: ActivityListSuchaz[];

    constructor(
        public activeModal: NgbActiveModal,
        private jhiAlertService: JhiAlertService,
        private suchAzUserService: SuchAzUserSuchazService,
        private traitService: TraitSuchazService,
        private userProfileService: UserProfileSuchazService,
        private wishListService: WishListSuchazService,
        private buyLaterListService: BuyLaterListSuchazService,
        private activityListService: ActivityListSuchazService,
        private eventManager: JhiEventManager
    ) {
    }

    ngOnInit() {
        this.isSaving = false;
        this.traitService.query()
            .subscribe((res: HttpResponse<TraitSuchaz[]>) => { this.traits = res.body; }, (res: HttpErrorResponse) => this.onError(res.message));
        this.userProfileService.query()
            .subscribe((res: HttpResponse<UserProfileSuchaz[]>) => { this.userprofiles = res.body; }, (res: HttpErrorResponse) => this.onError(res.message));
        this.wishListService.query()
            .subscribe((res: HttpResponse<WishListSuchaz[]>) => { this.wishlists = res.body; }, (res: HttpErrorResponse) => this.onError(res.message));
        this.buyLaterListService.query()
            .subscribe((res: HttpResponse<BuyLaterListSuchaz[]>) => { this.buylaterlists = res.body; }, (res: HttpErrorResponse) => this.onError(res.message));
        this.activityListService.query()
            .subscribe((res: HttpResponse<ActivityListSuchaz[]>) => { this.activitylists = res.body; }, (res: HttpErrorResponse) => this.onError(res.message));
    }

    clear() {
        this.activeModal.dismiss('cancel');
    }

    save() {
        this.isSaving = true;
        if (this.suchAzUser.id !== undefined) {
            this.subscribeToSaveResponse(
                this.suchAzUserService.update(this.suchAzUser));
        } else {
            this.subscribeToSaveResponse(
                this.suchAzUserService.create(this.suchAzUser));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<SuchAzUserSuchaz>>) {
        result.subscribe((res: HttpResponse<SuchAzUserSuchaz>) =>
            this.onSaveSuccess(res.body), (res: HttpErrorResponse) => this.onSaveError());
    }

    private onSaveSuccess(result: SuchAzUserSuchaz) {
        this.eventManager.broadcast({ name: 'suchAzUserListModification', content: 'OK'});
        this.isSaving = false;
        this.activeModal.dismiss(result);
    }

    private onSaveError() {
        this.isSaving = false;
    }

    private onError(error: any) {
        this.jhiAlertService.error(error.message, null, null);
    }

    trackTraitById(index: number, item: TraitSuchaz) {
        return item.id;
    }

    trackUserProfileById(index: number, item: UserProfileSuchaz) {
        return item.id;
    }

    trackWishListById(index: number, item: WishListSuchaz) {
        return item.id;
    }

    trackBuyLaterListById(index: number, item: BuyLaterListSuchaz) {
        return item.id;
    }

    trackActivityListById(index: number, item: ActivityListSuchaz) {
        return item.id;
    }

    getSelected(selectedVals: Array<any>, option: any) {
        if (selectedVals) {
            for (let i = 0; i < selectedVals.length; i++) {
                if (option.id === selectedVals[i].id) {
                    return selectedVals[i];
                }
            }
        }
        return option;
    }
}

@Component({
    selector: 'jhi-such-az-user-suchaz-popup',
    template: ''
})
export class SuchAzUserSuchazPopupComponent implements OnInit, OnDestroy {

    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private suchAzUserPopupService: SuchAzUserSuchazPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            if ( params['id'] ) {
                this.suchAzUserPopupService
                    .open(SuchAzUserSuchazDialogComponent as Component, params['id']);
            } else {
                this.suchAzUserPopupService
                    .open(SuchAzUserSuchazDialogComponent as Component);
            }
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
