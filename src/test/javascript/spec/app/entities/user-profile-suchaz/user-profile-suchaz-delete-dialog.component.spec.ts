/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, async, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable } from 'rxjs/Observable';
import { JhiEventManager } from 'ng-jhipster';

import { SuchazapisTestModule } from '../../../test.module';
import { UserProfileSuchazDeleteDialogComponent } from '../../../../../../main/webapp/app/entities/user-profile-suchaz/user-profile-suchaz-delete-dialog.component';
import { UserProfileSuchazService } from '../../../../../../main/webapp/app/entities/user-profile-suchaz/user-profile-suchaz.service';

describe('Component Tests', () => {

    describe('UserProfileSuchaz Management Delete Component', () => {
        let comp: UserProfileSuchazDeleteDialogComponent;
        let fixture: ComponentFixture<UserProfileSuchazDeleteDialogComponent>;
        let service: UserProfileSuchazService;
        let mockEventManager: any;
        let mockActiveModal: any;

        beforeEach(async(() => {
            TestBed.configureTestingModule({
                imports: [SuchazapisTestModule],
                declarations: [UserProfileSuchazDeleteDialogComponent],
                providers: [
                    UserProfileSuchazService
                ]
            })
            .overrideTemplate(UserProfileSuchazDeleteDialogComponent, '')
            .compileComponents();
        }));

        beforeEach(() => {
            fixture = TestBed.createComponent(UserProfileSuchazDeleteDialogComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(UserProfileSuchazService);
            mockEventManager = fixture.debugElement.injector.get(JhiEventManager);
            mockActiveModal = fixture.debugElement.injector.get(NgbActiveModal);
        });

        describe('confirmDelete', () => {
            it('Should call delete service on confirmDelete',
                inject([],
                    fakeAsync(() => {
                        // GIVEN
                        spyOn(service, 'delete').and.returnValue(Observable.of({}));

                        // WHEN
                        comp.confirmDelete(123);
                        tick();

                        // THEN
                        expect(service.delete).toHaveBeenCalledWith(123);
                        expect(mockActiveModal.dismissSpy).toHaveBeenCalled();
                        expect(mockEventManager.broadcastSpy).toHaveBeenCalled();
                    })
                )
            );
        });
    });

});
