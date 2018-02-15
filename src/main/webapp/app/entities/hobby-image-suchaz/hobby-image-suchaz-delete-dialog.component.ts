import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { HobbyImageSuchaz } from './hobby-image-suchaz.model';
import { HobbyImageSuchazPopupService } from './hobby-image-suchaz-popup.service';
import { HobbyImageSuchazService } from './hobby-image-suchaz.service';

@Component({
    selector: 'jhi-hobby-image-suchaz-delete-dialog',
    templateUrl: './hobby-image-suchaz-delete-dialog.component.html'
})
export class HobbyImageSuchazDeleteDialogComponent {

    hobbyImage: HobbyImageSuchaz;

    constructor(
        private hobbyImageService: HobbyImageSuchazService,
        public activeModal: NgbActiveModal,
        private eventManager: JhiEventManager
    ) {
    }

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.hobbyImageService.delete(id).subscribe((response) => {
            this.eventManager.broadcast({
                name: 'hobbyImageListModification',
                content: 'Deleted an hobbyImage'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-hobby-image-suchaz-delete-popup',
    template: ''
})
export class HobbyImageSuchazDeletePopupComponent implements OnInit, OnDestroy {

    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private hobbyImagePopupService: HobbyImageSuchazPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            this.hobbyImagePopupService
                .open(HobbyImageSuchazDeleteDialogComponent as Component, params['id']);
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
