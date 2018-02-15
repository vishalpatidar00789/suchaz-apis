import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { WishListSuchaz } from './wish-list-suchaz.model';
import { WishListSuchazPopupService } from './wish-list-suchaz-popup.service';
import { WishListSuchazService } from './wish-list-suchaz.service';

@Component({
    selector: 'jhi-wish-list-suchaz-delete-dialog',
    templateUrl: './wish-list-suchaz-delete-dialog.component.html'
})
export class WishListSuchazDeleteDialogComponent {

    wishList: WishListSuchaz;

    constructor(
        private wishListService: WishListSuchazService,
        public activeModal: NgbActiveModal,
        private eventManager: JhiEventManager
    ) {
    }

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.wishListService.delete(id).subscribe((response) => {
            this.eventManager.broadcast({
                name: 'wishListListModification',
                content: 'Deleted an wishList'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-wish-list-suchaz-delete-popup',
    template: ''
})
export class WishListSuchazDeletePopupComponent implements OnInit, OnDestroy {

    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private wishListPopupService: WishListSuchazPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            this.wishListPopupService
                .open(WishListSuchazDeleteDialogComponent as Component, params['id']);
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
